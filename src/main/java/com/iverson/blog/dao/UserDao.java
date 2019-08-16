package com.iverson.blog.dao;

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
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 据用户名分页查询用户列表(模糊查询)
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 据用户名查找用户
     * @param name
     * @return
     */
    User findByUsername(String name);
}
