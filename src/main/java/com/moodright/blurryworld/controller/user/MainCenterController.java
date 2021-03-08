package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页面控制器
 * @author moodright
 * @date 2021/3/2
 */
@Controller
@RequestMapping("/main")
public class MainCenterController {

    /**
     * 用户主界面Mapping
     * @return 用户主界面模板
     */
    @GetMapping
    public String main() {
        return "/user/user-main";
    }
}
