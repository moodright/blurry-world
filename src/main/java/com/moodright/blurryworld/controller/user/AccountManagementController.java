package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.config.AvatarUploadConfig;
import com.moodright.blurryworld.pojo.Profile;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.ProfileService;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.Date;

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
    ProfileService profileService;

    @Autowired
    public void setAvatarUploadConfig(AvatarUploadConfig avatarUploadConfig) {
        this.avatarUploadConfig = avatarUploadConfig;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

//    /**
//     * 账号管理主页面
//     * @return 个人信息页面模板
//     */
//    @GetMapping
//    public String homepage(HttpSession session, Model model) {
//        return "/user/account-management/homepage";
//    }

    /**
     * 修改个人信息页面
     * @return 个人信息页面模板
     */
    @GetMapping("setting")
    public String settingInfo(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        Profile profile = profileService.queryProfileById(user.getUserId());
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
        return "/user/account-management/homepage";
    }

    /**
     * 更新个人信息
     * @param nickname 别名
     * @param signature 个性签名
     * @param gender 性别
     * @param birthday  生日
     * @param province  省份
     * @param city  城市
     * @param email 联系邮箱
     * @return 修改个人信息页面
     */
    @PostMapping("setting/update")
    public String settingInfo(@RequestParam("nickname")String nickname,
                              @RequestParam("signature")String signature,
                              @RequestParam("gender")int gender,
                              @RequestParam("birthday")String birthday,
                              @RequestParam("input_province")String province,
                              @RequestParam("input_city")String city,
                              @RequestParam("email")String email,
                              HttpSession session) {
        // 封装用户信息
        User user = (User)session.getAttribute("user");
        user.setNickname(nickname);
        user.setGender(gender);
        // 更新至用户表
        userService.updateUserById(user);
        // 封装个人信息
        Profile profile = new Profile();
        profile.setUserId(user.getUserId());
        profile.setSignature(signature);
        profile.setEmail(email);
        profile.setProvince(province);
        profile.setCity(city);
        // 判断日期是否为空字符串
        if(!birthday.equals("")) {
             profile.setBirthday(DateUtil.birthdayStringToDate(birthday));
        }
        // 更新至个人信息表
        profileService.updateProfileById(profile);
        return "redirect:/account/setting";
    }

    /**
     * 更新头像页面
     * @return 头像页面模板
     */
    @GetMapping("avatar")
    public String avatar(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        Profile profile = profileService.queryProfileById(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        return "/user/account-management/avatar";
    }

    /**
     * 更新头像
     * @param avatar 头像
     * @return 头像页面模板
     */
    @PostMapping("avatar/update")
    public String updateAvatar(@RequestParam("avatar")MultipartFile avatar, HttpSession session) throws IOException {
        if(!avatar.isEmpty()) {
            // 获取当前会话的用户对象
            User user = (User)session.getAttribute("user");
            // 定义保存在本地的头像文件名
            // 待办：保存文件名没有变化，会导致浏览器取用缓存里的头像，而不显示更新后的头像资源
            //      暂时将命名规则更改为动态的
            String saveFileName = user.getUserId() + "" + new Date().getTime() +  ".png";
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
        }
        return "redirect:/account/avatar";
    }

    /**
     * 修改外部链接
     * @return 外部链接模板
     */
    @GetMapping("/link")
    public String link(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        Profile profile = profileService.queryProfileById(user.getUserId());
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
        return "/user/account-management/link";
    }

    /**
     * 更新外部链接
     * @param github github链接
     * @param website 个人网站链接
     * @param weibo 微博链接
     * @param session 查询当前会话的用户对象
     * @param model 数据回显
     * @return 重定向 @GetMapping("/link")
     */
    @PostMapping("/link/update")
    public String updateLink(@RequestParam("github")String github,
                             @RequestParam("personal-website")String website,
                             @RequestParam("weibo")String weibo,
                             HttpSession session,
                             Model model) {
        User user = (User)session.getAttribute("user");
        Profile profile = new Profile();
        profile.setGithubLink(github);
        profile.setWebsiteLink(website);
        profile.setWeiboLink(weibo);
        profile.setUserId(user.getUserId());
        // 更新信息
        profileService.updateProfileById(profile);
        // 数据回显
        profile = profileService.queryProfileById(user.getUserId());
        model.addAttribute("profile", profile);
        return "redirect:/account/link";
    }

}
