package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.Map;

/**
 * 文章管理控制器
 * @author moodright
 * @date 2021/3/7
 */
@Controller
@RequestMapping("post")
public class PostManagementController {

    Map<String, Object> post = new HashMap<>();

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
     * @return 显示文章Mapping
     */
    @PostMapping("write")
    public String writePost(@RequestParam("post-title")String title, @RequestParam("editormd-markdown-doc")String content) {
        System.out.println("title=>" + title);
        System.out.println("content=>" + content);
        // 封装文章数据
        post.put("title", title);
        post.put("content", content);

        return "redirect:/post/read";
    }

    /**
     * 浏览文章
     * @param model 存放文章的模型
     * @return 显示文章模板
     */
    @GetMapping("read")
    public String displayPost(Model model) {
        model.addAttribute("post", post);
        return "/user/post-management/display-post";
    }

}
