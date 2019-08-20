package com.iverson.blog.service.impl;

import com.iverson.blog.dao.CatalogDao;
import com.iverson.blog.entity.Catalog;
import com.iverson.blog.entity.User;
import com.iverson.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    public List<Catalog> listCatalogs(User user) {
        return catalogDao.findByUser(user);
    }

    @Override
    public Catalog getCatalogById(Long catalogId) {
        return catalogDao.findOne(catalogId);
    }

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        List<Catalog> catalogs = catalogDao.findByUserAndName(catalog.getUser(), catalog.getName());
        if (catalogs != null && catalogs.size() > 0) {
            throw new IllegalArgumentException("该分类已经存在了");
        }
        return catalogDao.save(catalog);
    }

    @Override
    public void removeCatalog(Long id) {
        catalogDao.delete(id);
    }
}
