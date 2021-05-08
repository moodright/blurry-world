package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/5/8
 */
@Controller
@RequestMapping("search")
public class SearchEngineController {

    /**
     * 搜索主页面
     * @return 搜索主页面模板
     */
    @GetMapping
    public String searchTemplateIndex() {
        return "/user/search-engine/search-index";
    }

    /**
     * 搜索文章列表
     * @return 搜索文章页面模板
     */
    @GetMapping("post")
    public String searchPostIndex() {
        return "/user/search-engine/search-post";
    }

    /**
     * 搜索用户列表
     * @return 搜索用户页面模板
     */
    @GetMapping("user")
    public String searchUserIndex() {
        return "/user/search-engine/search-user";
    }
}
