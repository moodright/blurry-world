package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号管理控制器
 * @author moodright
 * @date 2021/3/6
 */
@Controller
@RequestMapping("account")
public class AccountManagementController {

    /**
     * 账号管理主页面
     * @return 个人信息页面模板
     */
    @GetMapping
    public String homepage() {
        return "/user/account-management/homepage";
    }

    /**
     * 修改个人信息页面
     * @return 个人信息页面模板
     */
    @GetMapping("setting")
    public String settingInfo() {
        return "/user/account-management/homepage";
    }

    /**
     * 更新头像页面
     * @return 头像页面模板
     */
    @GetMapping("avatar")
    public String avatar() {
        return "/user/account-management/avatar";
    }
}
