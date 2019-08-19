package com.iverson.blog.controller;

import com.iverson.blog.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理控制器
 *
 * @author Iverson
 * @class AdminController
 * @date 2019/8/15
 */
@Controller
@RequestMapping("/admins")
public class AdminController {
    /**
     * 获取后台管理主页面
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listUser(Model model) {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("用户管理","/users"));
        model.addAttribute("list", menus);
        return new ModelAndView("/admins/index", "model", model);
    }
}
