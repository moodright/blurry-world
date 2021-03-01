package com.moodright.blurryworld.controller.admin;

import com.moodright.blurryworld.dao.UserDao;
import com.moodright.blurryworld.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author moodright
 * @date 2021/3/1
 */

@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 用户管理页面跳转
     * @return 重定向至查询全部用户的Mapping -> allUsers()
     */
    @GetMapping
    public String userManagement() {
        return "redirect:/admin/user/all";
    }

    /**
     * 查询全部用户
     * @param model 模型：存储用户列表
     * @return 用户管理模板
     */
    @GetMapping(path = "/all")
    public String allUsers(Model model) {
        List<User> userList = userDao.queryAllUser();
        model.addAttribute("userList", userList);
        return "admin/user-management";

    }

    /**
     * 异步请求
     * 根据用户ID删除用户
     * @param id 用户编号
     * @return 是否删除判断字段
     */
    @GetMapping(path = "/delete/{id}")
    @ResponseBody
    public int deleteUserById(@PathVariable("id")Integer id) {
        if(userDao.deleteUserById(id) == 1) {
            // 删除成功
            return 1;
        }else {
            // 删除失败
            return 0;
        }
    }

    /**
     * 添加用户
     * @param username 用户名
     * @param password 密码
     * @param nickname 别名
     * @param gender 性别
     * @return 重定向至查询全部用户的Mapping -> allUsers()
     */
    @PostMapping(path = "/add")
    public String addUser(@RequestParam("username")String username,
                          @RequestParam("password")String password,
                          @RequestParam("nickname")String nickname,
                          @RequestParam("gender")Integer gender) {
        User user = new User(userDao.getPrimaryKey(), username, password, nickname, gender);
        userDao.addUser(user);
        return "redirect:/admin/user/all";
    }

    /**
     * 异步请求
     * 查询需要更新的用户信息
     * @param id 用户编号
     * @return 需要更新的用户对象
     */
    @GetMapping(path = "/update/{id}")
    @ResponseBody
    public User updateUser(@PathVariable Integer id) {
        return userDao.queryUserById(id);
    }

    /**
     * 更新用户信息
     * @param id 用户编号
     * @param username 用户名
     * @param password 用户密码
     * @param nickname 别名
     * @param gender 性别
     * @param accountStatus 账户状态
     * @return 重定向至查询全部用户的Mapping -> allUsers()
     */
    @PostMapping(path = "/update/{id}")
    public String updateUser(@PathVariable Integer id,
                             @RequestParam("update_username")String username,
                             @RequestParam("update_password")String password,
                             @RequestParam("update_nickname")String nickname,
                             @RequestParam("update_gender")Integer gender,
                             @RequestParam("update_accountStatus")Boolean accountStatus) {
        // 查询用户旧的信息
        User oldUserInfo = userDao.queryUserById(id);
        // 更新用户信息
        oldUserInfo.setUsername(username);
        oldUserInfo.setPassword(password);
        oldUserInfo.setNickname(nickname);
        oldUserInfo.setGender(gender);
        oldUserInfo.setAccountIsDeleted(accountStatus);
        userDao.updateUser(id, oldUserInfo);
        return "redirect:/admin/user/all";
    }

}
