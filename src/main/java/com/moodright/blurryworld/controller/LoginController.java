package com.moodright.blurryworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author moodright
 * @date 2021/2/27
 */

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String login() {
        return "user/login";
    }

    @GetMapping(path = "/interceptor")
    public String interceptor() {
        return "user/interceptor";
    }

}
