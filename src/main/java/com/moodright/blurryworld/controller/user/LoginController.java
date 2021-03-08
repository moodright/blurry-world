package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录控制器
 * @author moodright
 * @date 2021/2/27
 */

@Controller
public class LoginController {

    /**
     * 登录
     * @return 登录模板
     */
    @GetMapping(path = "/login")
    public String login() {
        return "user/login";
    }

    /**
     * 拦截器
     * @return 拦截器模板
     */
    @GetMapping(path = "/interceptor")
    public String interceptor() {
        return "user/interceptor";
    }

}
