package com.iverson.blog.controller;

import com.iverson.blog.entity.Authority;
import com.iverson.blog.entity.User;
import com.iverson.blog.service.AuthorityService;
import com.iverson.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 主页跳转控制器
 *
 * @author Iverson
 * @version 1.00
 * @date 2019/8/15
 */
@Controller
public class MainController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

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

    /**
     * 用户注册
     * @param user
     * @return
     */
   @PostMapping("/register")
   public String registerUser(User user) {
       List<Authority> authorities = new ArrayList<>();
       authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
       user.setAuthorities(authorities);
       userService.saveUser(user);
       return "redirect:/login";
   }
}
