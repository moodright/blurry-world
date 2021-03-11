package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.config.AvatarUploadConfig;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 账号管理控制器
 * @author moodright
 * @date 2021/3/6
 */
@Controller
@RequestMapping("account")
public class AccountManagementController {

    AvatarUploadConfig avatarUploadConfig;
    UserService userService;

    @Autowired
    public void setAvatarUploadConfig(AvatarUploadConfig avatarUploadConfig) {
        this.avatarUploadConfig = avatarUploadConfig;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

    /**
     * 更新头像
     * @param avatar 头像
     * @return 头像页面模板
     */
    @PostMapping("avatar/update")
    public String updateAvatar(@RequestParam("avatar")MultipartFile avatar, HttpSession session) throws IOException {
        // 获取文件名
        String originalFileName = avatar.getOriginalFilename();
        // 获取文件拓展名
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 获取当前会话的用户对象
        User user = (User)session.getAttribute("user");
        // 定义保存在本地的头像文件名
        String saveFileName = user.getUserId() + extensionName;
        // 创建路径
        Path path = Paths.get(avatarUploadConfig.getStorage(), saveFileName);
        // 写入文件比特流至具体位置
        Files.write(path, avatar.getBytes());
        avatar.transferTo(Paths.get(avatarUploadConfig.getStorage(), saveFileName).toFile());
        // 创建头像url
        // avatarUrl=>http://localhost:8090/public/avatar/1001.png
        String avatarUrl = avatarUploadConfig.getHost() + avatarUploadConfig.getUrlPrefix() + saveFileName;
        // 将头像url更新至数据库
        user.setAvatar(avatarUrl);
        userService.updateAvatar(user);
        return "redirect:/account/avatar";
    }
}
