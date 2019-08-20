package com.iverson.blog.service.impl;

import com.iverson.blog.dao.BlogDao;
import com.iverson.blog.entity.Blog;
import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;
import com.iverson.blog.entity.es.EsBlog;
import com.iverson.blog.service.BlogService;
import com.iverson.blog.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private EsBlogService esBlogService;

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.findOne(id);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        boolean isNew = StringUtils.isEmpty(blog.getId());
        EsBlog esBlog = null;
        Blog returnBlog = blogDao.save(blog);
        if(isNew) {
            esBlog = new EsBlog(returnBlog);
        }else {
            esBlog = esBlogService.getEsBlogById(blog.getId());
            esBlog.update(returnBlog);
        }
        esBlogService.updateEsBlog(esBlog);
        return returnBlog;
    }

    @Override
    public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
        Page<Blog> blogPage = blogDao.findByCatalog(catalog, pageable);
        return blogPage;
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String keyword, Pageable pageable) {
        keyword = "%" + keyword + "%";
        Page<Blog> blogPage = blogDao.findByUserAndTitleLike(user,keyword,pageable);
        return blogPage;
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String keyword, Pageable pageable) {
        keyword = "%" + keyword + "%";
        String tags = keyword;
        Page<Blog> blogPage = blogDao.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(keyword, user, tags, user, pageable);
        return blogPage;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogDao.findOne(id);
        blog.setReadSize(blog.getCommentSize() + 1);
        this.saveBlog(blog);
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogDao.delete(id);
        EsBlog esBlog = esBlogService.getEsBlogById(id);
        esBlogService.removeEsBlog(esBlog.getId());
    }
}
