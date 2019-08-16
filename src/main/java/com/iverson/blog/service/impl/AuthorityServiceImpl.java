package com.iverson.blog.service.impl;

import com.iverson.blog.dao.AuthorityDao;
import com.iverson.blog.entity.Authority;
import com.iverson.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityDao.getOne(id);
    }
}
