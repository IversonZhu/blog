package com.iverson.blog.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Description: 目录实体类
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/15
 */

@Entity
@Data
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "名称不能为空")
    @Size(min=2, max=30)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String name;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    protected Catalog() {}

    public Catalog(User user, String name) {
        this.user = user;
        this.name = name;
    }
}
