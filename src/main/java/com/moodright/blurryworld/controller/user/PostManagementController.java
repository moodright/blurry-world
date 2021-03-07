package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author moodright
 * @date 2021/3/7
 */
@Controller
@RequestMapping("post")
public class PostManagementController {

    /**
     * 编写文章
     * @return 编辑文章页面模板
     */
    @GetMapping
    public String editPostPage() {
        return "/user/post-management/edit-post";
    }

    /**
     * 上传文章
     * @param title 文章标题
     * @param content 文章内容
     * @return 用户个人中心模板
     */
    @PostMapping("write")
    public String writePost(@RequestParam("post-title")String title, @RequestParam("editormd-markdown-doc")String content) {
        System.out.println("title=>" + title);
        System.out.println("content=>" + content);
        return "/user/personal-center/postlist";
    }

}
