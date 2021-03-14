package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.Profile;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.ProfileService;
import com.moodright.blurryworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 个人中心控制器
 * @author moodright
 * @date 2021/3/4
 */
@Controller
@RequestMapping("/space")
public class PersonalCenterController {

    UserService userService;
    ProfileService profileService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * 用户个人主页 默认返回文章列表
     * @return 个人主页文章列表模板
     */
    @GetMapping("/{id}")
    public String personalCenter(@PathVariable("id")Integer userId, Model model) {
        // 待办：访问无效的用户id跳转到404页面
        // ------------------------------
        User user = userService.queryUserById(userId);
        Profile profile = profileService.queryProfileById(userId);
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
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
