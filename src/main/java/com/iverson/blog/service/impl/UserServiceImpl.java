package com.iverson.blog.service.impl;

import com.iverson.blog.dao.UserDao;
import com.iverson.blog.entity.User;
import com.iverson.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Description: 用户服务接口实现类
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void removeUsersInBatch(List<User> users) {
        userDao.deleteInBatch(users);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    public Page<User> listUserByNameLike(String name, Pageable pageable) {
        name = "%" + name + "%";
        return userDao.findByNameLike(name,pageable);
    }
}