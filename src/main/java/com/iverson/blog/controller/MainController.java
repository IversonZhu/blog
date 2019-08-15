package com.iverson.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 页面跳转控制器
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/15
 */
@Controller("/main")
public class MainController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
