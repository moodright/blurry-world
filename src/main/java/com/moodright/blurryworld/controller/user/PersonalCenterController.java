package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 个人中心控制器
 * @author moodright
 * @date 2021/3/4
 */
@Controller
@RequestMapping("/space")
public class PersonalCenterController {


    /**
     * 用户个人主页 默认返回文章列表
     * @return 个人主页文章列表模板
     */
    @GetMapping
    public String personalCenter() {
        return "/user/personal-center/postlist";
    }

    /**
     * 用户个人主页文章
     * @return 个人主页文章列表模板
     */
    @GetMapping("post")
    public String personalCenterPostList() {
        return "/user/personal-center/postlist";
    }

    /**
     * 用户个人主页动态
     * @return 个人主页动态列表模板
     */
    @GetMapping("chat")
    public String personalCenterChatList() {
        return "/user/personal-center/chatlist";
    }

    /**
     * 用户个人主页相册
     * @return 个人主页相册模板
     */
    @GetMapping("album")
    public String personalCenterAlbumList() {
        return "/user/personal-center/albumlist";
    }


}
