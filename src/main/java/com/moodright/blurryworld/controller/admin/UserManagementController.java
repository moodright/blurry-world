package com.moodright.blurryworld.controller.admin;

import com.moodright.blurryworld.dao.UserDao;
import com.moodright.blurryworld.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
     * @param model 存储用户列表
     * @return 用户管理模板
     */
    @GetMapping(path = "/all")
    public String allUsers(Model model) {
        List<User> userList = userDao.queryAllUser();
        model.addAttribute("userList", userList);
        return "admin/user-management";

    }

    /**
     * 根域用户ID删除用户
     * @param id 用户编号
     * @return 删除是否成功判断字段
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


}
