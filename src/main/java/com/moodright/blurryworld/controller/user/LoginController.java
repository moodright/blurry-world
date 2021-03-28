package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 * @author moodright
 * @date 2021/2/27
 */

@Controller
public class LoginController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 跳转登录页面
     * @return 登录模板
     */
    @GetMapping(path = "/login")
    public String LoginPage() {
        return "user/login";
    }


    /**
     * 登录账号
     * @param username 用户名
     * @param password 密码
     * @return PersonalCenterController -> /space/host Mapping
     */
    @PostMapping(path = "/login")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session, Model model) {
        // 登录校验
        User user = userService.queryUserByUsername(username);
        if( user == null) {
            model.addAttribute("msg", "用户不存在，请重新登录");
            return "user/login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("msg", "密码错误，请重新登录");
            return "user/login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/space/" + user.getUserId();
        }
    }


    @GetMapping(path="/exit")
    public String signOut(HttpSession session) {
        // 注销会话
        session.invalidate();
        return "user/login";
    }
}
