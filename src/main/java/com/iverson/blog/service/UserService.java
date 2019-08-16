package com.iverson.blog.service;

import com.iverson.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description: 用户服务接口
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 删除列表里的用户
     * @param users
     */
    void removeUsersInBatch(List<User> users);

    /**
     * 据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 据用户名分页查询用户列表(模糊查询)
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUserByNameLike(String name, Pageable pageable);
}
