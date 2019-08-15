package com.iverson.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Iverson
 * @class BlogController
 * @date 2019/8/15
 */
@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogController {

    @GetMapping("/list")
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order, @RequestParam(value = "keyword", required = false) Long keyword) {
        return "redirect:/index?order=" + order + "&keyword=" + keyword;
    }
}
