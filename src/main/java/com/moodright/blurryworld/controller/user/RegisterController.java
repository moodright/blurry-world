package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.ProfileService;
import com.moodright.blurryworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 注册控制器
 * @author moodright
 * @date 2021/3/11
 */
@Controller
public class RegisterController {

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
     * 跳转注册页面
     * @return 登录模板（注册模态框默认开启）
     */
    @GetMapping(path= "/register")
    public String registerPage(Model model) {
        // 注册模态框开启判断字段
        model.addAttribute("register", true);
        return "user/login";
    }

    /**
     * 验证用户名异步请求
     * @param username 用户名
     * @return 判断字段 0：已存在
     *                1：不存在
     */
    @PostMapping(path = "/register/validate")
    @ResponseBody
    public String validate(@RequestParam("username")String username) {
        User user = userService.queryUserByUsername(username);
        if(user != null) {
            return "0";
        }
        return "1";
    }

    /**
     * 注册账号
     * @param username 用户名
     * @param password 密码
     * @return PersonalCenterController -> /space/host Mapping
     */
    @PostMapping(path = "/register")
    public String register(@RequestParam("register_username")String username, @RequestParam("register_password")String password, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 添加该用户至 User 表中
        userService.addUser(user);
        user = userService.queryUserByUsername(username);
        // 将用户添加到会话中
        session.setAttribute("user", user);
        // 个人信息初始化
        profileService.addProfileById(user.getUserId());
        return "redirect:main";
    }
}
