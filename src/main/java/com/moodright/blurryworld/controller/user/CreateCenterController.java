package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/3/17
 */

@Controller
@RequestMapping("platform")
public class CreateCenterController {
    @GetMapping
    public String createCenterIndex() {
        return "/user/post-management/create-center";
    }
}
