package com.iverson.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户主页控制器
 *
 * @author Iverson
 * @class UserSpaceController
 * @date 2019/8/15
 */
@Controller
@RequestMapping("/u")
public class UserSpaceController {

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        return "/userspace/user";
    }

    @GetMapping("/{username}")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "category", required = false) Long category,
                                   @RequestParam(value = "keyword", required = false) String keyword) {
        if(category != null) {
            return "/userspace/u";
        }else if(!StringUtils.isEmpty(keyword)) {
            return "/userspace/u";
        }
        return "/userspace/u";
    }
}
