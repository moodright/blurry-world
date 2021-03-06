package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author moodright
 * @date 2021/3/6
 */
@Controller
@RequestMapping("account")
public class AccountManagementController {

    @GetMapping("home")
    public String homepage() {
        return "/user/account-management/homepage";
    }
}
