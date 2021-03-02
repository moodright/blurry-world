package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/3/2
 */
@Controller
@RequestMapping("/main")
public class MainCenterController {

    @GetMapping
    public String main() {
        return "/user/user-main";
    }
}
