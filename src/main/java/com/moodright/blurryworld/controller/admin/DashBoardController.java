package com.moodright.blurryworld.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/2/28
 */
@Controller
@RequestMapping("/admin")
public class DashBoardController {

    // 后台管理主页面
    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    // 用户管理页面跳转
    @GetMapping("/user")
    public String userManagement() {
        return "admin/user-management";
    }

}
