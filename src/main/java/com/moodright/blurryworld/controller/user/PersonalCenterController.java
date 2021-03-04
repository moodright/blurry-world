package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/3/4
 */
@Controller
@RequestMapping("/user")
public class PersonalCenterController {


    /**
     * 用户个人主页动态
     * @return 个人主页动态列表模板
     */
    @GetMapping
    public String personalCenter() {
        return "/user/user-personal-center-chatlist";
    }

    /**
     * 用户个人主页文章
     * @return 个人主页文章列表模板
     */
    @GetMapping("post")
    public String personalCenterPostList() {
        return "/user/user-personal-center-postlist";
    }

    @GetMapping("album")
    public String personalCenterAlbumList() {
        return "/user/user-personal-center-albumlist";
    }

}
