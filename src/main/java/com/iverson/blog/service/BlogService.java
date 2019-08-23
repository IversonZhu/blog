package com.iverson.blog.service;

import com.iverson.blog.entity.Blog;
import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;
import com.iverson.blog.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface BlogService {
    /**
     * 据ID获取Blog
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 保存Blog
     * @param originalBlog
     * @return
     */
    Blog saveBlog(Blog originalBlog);

    /**
     * 据分类查询Blogs
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable);

    /**
     * 根据用户名进行分页模糊查询（最热）
     * @param user
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVoteAndSort(User user, String keyword, Pageable pageable);

    /**
     * 根据用户名进行分页模糊查询（最新）
     * @param user
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVote(User user, String keyword, Pageable pageable);

    /**
     * 阅读次数递增
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * 据ID删除Blog
     * @param id
     */
    void removeBlog(Long id);

    /**
     * 发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * 删除评论
     * @param blogId
     * @param id
     */
    void removeComment(Long blogId, Long id);

    /**
     * 点赞
     * @param blogId
     */
    Blog createVote(Long blogId);

    /**
     * 取消点赞
     * @param blogId
     * @param id
     */
    void removeVote(Long blogId, Long id);
}
