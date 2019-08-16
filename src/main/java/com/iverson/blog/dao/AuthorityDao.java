package com.iverson.blog.dao;

import com.iverson.blog.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
