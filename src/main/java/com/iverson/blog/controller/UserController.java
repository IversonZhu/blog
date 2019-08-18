package com.iverson.blog.controller;

import com.iverson.blog.entity.Authority;
import com.iverson.blog.entity.User;
import com.iverson.blog.handler.ConstraintViolationExceptionHandler;
import com.iverson.blog.service.AuthorityService;
import com.iverson.blog.service.UserService;
import com.iverson.blog.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iverson
 * @class UserController
 * @date 2019/8/15
 */
@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 分页查看用户列表
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                             Model model) {
        Pageable pageable = PageRequest.of(pageIndex,pageSize);
        Page<User> page = userService.listUserByNameLike(name, pageable);
        List<User> users = page.getContent();
        log.info("result = {}", users.get(0).toString());
        model.addAttribute("page",page);
        model.addAttribute("userList", users);
        return new ModelAndView(async ? "users/list :: #mainContainerRepleace" : "users/list", "userModel",model);
    }

    /**
     * 获取form表单页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user",new User(null,null,null,null));
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 新建用户
     * @param user
     * @param authorityId
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user, Long authorityId) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        if(user.getId() == null) {
            user.setEncodePassword(user.getPassword());
        }else {
            User originalUser = userService.getUserById(user.getId());
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean isMatch = encoder.matches(rawPassword,encodePasswd);
            if(!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }
        try {
            userService.saveUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMesssage(e)));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功", user));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try{
            userService.removeUser(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true,"处理成功"));
    }

    /**
     * 获取修改用户的界面，及数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("/users/edit", "userModel", model);
    }
}
