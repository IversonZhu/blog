package com.iverson.blog.controller;

import com.iverson.blog.entity.User;
import com.iverson.blog.entity.es.EsBlog;
import com.iverson.blog.service.EsBlogService;
import com.iverson.blog.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Iverson
 * @class BlogController
 * @date 2019/8/15
 */
@Controller
@RequestMapping("/blogs")
@Slf4j
public class BlogController {

    @Autowired
    private EsBlogService esBlogService;

    /**
     * 首页Blog列表
     * @param order
     * @param keyword
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping
    public String listEsBlog(@RequestParam(value="order",required=false,defaultValue="new") String order,
                             @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
                             @RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             Model model) {
        Page<EsBlog> esBlogPage = null;
        List<EsBlog> esBlogs = null;
        boolean isEmpty = true;
        try {
            if(order.equals("hot")) {//热门查询
                Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                esBlogPage = esBlogService.listHottestEsBlogs(keyword,pageable);
            } else if (order.equals("new")) {
                Sort sort = new Sort(Sort.Direction.DESC, "createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                esBlogPage = esBlogService.listNewestEsBlogs(keyword, pageable);
            }
            isEmpty = false;
        } catch (Exception e) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            esBlogPage = esBlogService.listEsBlogs(pageable);
        }
        esBlogs = esBlogPage.getContent();
        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", esBlogPage);
        model.addAttribute("blogList", esBlogs);
        // 首次访问页面才加载
        if (!async && !isEmpty) {
            List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
            model.addAttribute("newest", newest);
            List<EsBlog> hottest = esBlogService.listTop5HottestEsBlogs();
            model.addAttribute("hottest", hottest);
            List<TagVO> tags = esBlogService.listTop30Tags();
            model.addAttribute("tags", tags);
            List<User> users = esBlogService.listTop12Users();
            model.addAttribute("users", users);
        }
        return (async ? "/index :: #mainContainerRepleace" : "/index");
    }

    /**
     * 最新(前五)
     * @param model
     * @return
     */
    @GetMapping("/newest")
    public String listNewestEsBlogs(Model model) {
        List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
        model.addAttribute("newest", newest);
        return "newest";
    }

    /**
     * 最热(前五)
     * @param model
     * @return
     */
    @GetMapping("/hottest")
    public String listHottestEsBlogs(Model model) {
        List<EsBlog> hottest = esBlogService.listTop5HottestEsBlogs();
        model.addAttribute("hottest", hottest);
        return "hottest";
    }
}
