package com.iverson.blog.service.impl;

import com.iverson.blog.dao.EsBlogDao;
import com.iverson.blog.entity.User;
import com.iverson.blog.entity.es.EsBlog;
import com.iverson.blog.service.EsBlogService;
import com.iverson.blog.service.UserService;
import com.iverson.blog.vo.TagVO;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/20
 */
@Service
public class EsBlogServiceImpl implements EsBlogService {

    private static final Pageable TOP_5_PAGEABLE = new PageRequest(0, 5);
    private static final String EMPTY_KEYWORD = "";

    @Autowired
    private EsBlogDao esBlogDao;

    @Autowired
    private UserService userService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public EsBlog updateEsBlog(EsBlog esBlog) {
        return esBlogDao.save(esBlog);
    }

    @Override
    public EsBlog getEsBlogById(Long id) {
        return esBlogDao.findById(id);
    }

    @Override
    public void removeEsBlog(String id) {
        esBlogDao.delete(id);
    }

    @Override
    public Page<EsBlog> listHottestEsBlogs(String keyword, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize","createTime");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);
        }
        return esBlogDao.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);
        }
        return esBlogDao.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsBlog> listEsBlogs(Pageable pageable) {
        return esBlogDao.findAll(pageable);
    }

    @Override
    public List<EsBlog> listTop5NewestEsBlogs() {
        Page<EsBlog> blogPage = this.listNewestEsBlogs(EMPTY_KEYWORD,TOP_5_PAGEABLE);
        return blogPage.getContent();
    }

    @Override
    public List<EsBlog> listTop5HottestEsBlogs() {
        Page<EsBlog> blogPage = this.listHottestEsBlogs(EMPTY_KEYWORD,TOP_5_PAGEABLE);
        return blogPage.getContent();
    }

    @Override
    public List<TagVO> listTop30Tags() {
        List<TagVO> tagVOS = new ArrayList<>();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_AND_FETCH)
                .withIndices("blog").withTypes("blog")
                .addAggregation(terms("tags").field("tags").order(Terms.Order.count(false)).size(30))
                .build();
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });

        StringTerms modelTerms = (StringTerms) aggregations.asMap().get("tags");

        Iterator<Terms.Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Terms.Bucket actionTypeBucket = modelBucketIt.next();
            tagVOS.add(new TagVO(actionTypeBucket.getKey().toString(),actionTypeBucket.getDocCount()));
        }
        return tagVOS;
    }

    @Override
    public List<User> listTop12Users() {
        List<String> usernameList = new ArrayList<>();
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_AND_FETCH)
                .withIndices("blog").withTypes("blog")
                .addAggregation(terms("users").field("username").order(Terms.Order.count(false)).size(12))
                .build();
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse searchResponse) {
                return searchResponse.getAggregations();
            }
        });

        StringTerms modelTerms = (StringTerms) aggregations.asMap().get("users");

        Iterator<Terms.Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Terms.Bucket actionTypeBucket = modelBucketIt.next();
            String username = actionTypeBucket.getKey().toString();
            usernameList.add(username);
        }
        List<User> users = userService.listUsersByUsernames(usernameList);
        return users;
    }
}
