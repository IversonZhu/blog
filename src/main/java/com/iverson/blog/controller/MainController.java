package com.iverson.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 主页跳转控制器
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/15
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
   public String login() {
        return "login";
   }

   @GetMapping("/login-error")
   public String loginError(Model model){
       model.addAttribute("loginError", true);
       model.addAttribute("errorMsg", "登录失败,用户名和密码错误!");
       return "login";
   }

   @GetMapping("/register")
   public String register() {
        return "register";
   }
}
