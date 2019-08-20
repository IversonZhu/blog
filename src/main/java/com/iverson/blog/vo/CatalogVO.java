package com.iverson.blog.vo;

import com.iverson.blog.entity.Catalog;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/20
 */
@Data
public class CatalogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private Catalog catalog;

    public CatalogVO() { }
}
