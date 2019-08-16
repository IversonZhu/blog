package com.iverson.blog.service;

import com.iverson.blog.entity.Authority;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface AuthorityService {

    /**
     * 根据id获取 Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
