package com.iverson.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/16
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
