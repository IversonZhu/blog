package com.iverson.blog.dao;

import com.iverson.blog.entity.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/20
 */
public interface EsBlogDao extends ElasticsearchRepository<EsBlog,String> {
    EsBlog findByBlogId(Long id);

    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String keyword, String keyword1, String keyword2, String keyword3, Pageable pageable);
}
