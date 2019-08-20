package com.iverson.blog.vo;

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
public class TagVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long count;

    public TagVO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
