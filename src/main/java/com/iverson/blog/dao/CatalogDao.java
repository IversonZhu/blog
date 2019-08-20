package com.iverson.blog.dao;

import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface CatalogDao extends JpaRepository<Catalog, Long> {

    /**
     * 据用户查询分类列表
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 据用户和用户名查询分类列表
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user, String name);
}
