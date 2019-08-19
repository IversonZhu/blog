package com.iverson.blog.controller;

import com.iverson.blog.entity.User;
import com.iverson.blog.service.BlogService;
import com.iverson.blog.service.CatalogService;
import com.iverson.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CatalogService catalogService;

//    @GetMapping("/{username}")
//    public String userSpace(@PathVariable("username") String username) {
//        return "/userspace/user";
//    }
//
//    @GetMapping("/{username}")
//    public String listBlogsByOrder(@PathVariable("username") String username,
//                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
//                                   @RequestParam(value = "category", required = false) Long category,
//                                   @RequestParam(value = "keyword", required = false) String keyword) {
//        if(category != null) {
//            return "/userspace/u";
//        }else if(!StringUtils.isEmpty(keyword)) {
//            return "/userspace/u";
//        }
//        return "/userspace/u";
//    }

    /**
     * 获取个人设置页面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("fileServerUrl");
        return new ModelAndView("/userspace/profile", "userModel",model);
    }

    /**
     * 保存个人设置信息
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username, User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }
        userService.saveUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user",user);
        return new ModelAndView("/userspace/avatar","userModel",model);
    }


}
