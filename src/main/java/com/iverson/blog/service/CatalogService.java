package com.iverson.blog.service;

import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;

import java.util.List;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
public interface CatalogService {

    /**
     * 据用户名查询分类列表
     * @param user
     * @return
     */
    List<Catalog> listCatalogs(User user);

    /**
     * 据ID查询Catalog
     * @param catalogId
     * @return
     */
    Catalog getCatalogById(Long catalogId);

    /**
     * 保存Catalog
     * @param catalog
     * @return
     */
    Catalog saveCatalog(Catalog catalog);

    /**
     * 据ID删除分类
     * @param id
     */
    void removeCatalog(Long id);
}
