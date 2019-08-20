package com.iverson.blog.dao;

import com.iverson.blog.entity.Blog;
import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface BlogDao extends JpaRepository<Blog, Long> {
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);

    Page<Blog> findByUserAndTitleLike(User user, String keyword, Pageable pageable);

    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String keyword, User user, String tags, User user1, Pageable pageable);
}
