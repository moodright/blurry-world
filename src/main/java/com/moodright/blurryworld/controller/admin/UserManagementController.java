package com.moodright.blurryworld.controller.admin;

import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.service.UserService;
import com.moodright.blurryworld.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.ToDoubleBiFunction;

/**
 * 后台用户管理
 * @author moodright
 * @date 2021/3/1
 */
@Controller
@RequestMapping("/admin/user")
public class UserManagementController {

    // 用户管理业务层
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 分页工具类
    PaginationUtil paginationUtil;

    @Autowired
    public void setPaginationUtil(PaginationUtil paginationUtil) {
        this.paginationUtil = paginationUtil;
        // 设置分页的数量并更新map
        paginationUtil.setPageSize(9);
        paginationUtil.updatePageSize(paginationUtil.getPageSize());
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
     * 分页查询用户
     * @param model 模型：存储用户列表
     * @return 用户管理模板
     */
    @GetMapping(path = {"/all/{index}", "/all"})
    public String allUsers(Model model, @PathVariable(value = "index", required = false)Integer currentStartIndex) {
        // 首次进入页面判断
        if(currentStartIndex == null) {
            paginationUtil.setCurrentStartIndex(0);
        }
        else {
            paginationUtil.setCurrentStartIndex(currentStartIndex);
        }
        // 更新下标
        paginationUtil.updateCurrentStartIndex(paginationUtil.getCurrentStartIndex());
        // 重新查询数据库判断总量是否发生变化
        paginationUtil.updateTotalCount(userService.queryAllUserCount());
        // 最后一页边界情况
        if (paginationUtil.getPaginationInfo().get("currentStartIndex") == paginationUtil.getTotalCount()) {
            // 假设每页查9个数据, 在遇到总数为19的数据量时,
            // 删除一个数据后总数据与尾页下标相等(0->9->18 == 18)
            // 则需要查询出前一页数据(18->9)
            paginationUtil.getPaginationInfo().put("currentStartIndex", paginationUtil.getCurrentStartIndex() - paginationUtil.getPageSize());
        }
        // 更新总页数、最后一页下标、下一页下标、上一页下标
        paginationUtil.updateTotalPage();
        paginationUtil.updateLastPageIndex();
        paginationUtil.updateNextPageIndex(paginationUtil.getCurrentStartIndex());
        paginationUtil.updatePreviousPageIndex(paginationUtil.getCurrentStartIndex());
        // 查询用户
        List<User> userList = userService.queryUserByPagination(paginationUtil.getPaginationInfo());
        // 将用户列表和更新后的分页数据添加到模型中
        model.addAttribute("userList", userList);
        model.addAttribute("paginationInfo", paginationUtil.getPaginationInfo());
        return "admin/user-management";
    }

    /**
     * 异步请求
     * 根据用户ID删除用户
     * @param userId 用户编号
     * @return 受影响的行数
     */
    @GetMapping(path = "/delete/{id}")
    @ResponseBody
    public int deleteUserById(@PathVariable("id")Integer userId) {
        return userService.deleteUserById(userId);
    }

    /**
     * 添加用户
     * @param user 用户对象
     * @return 重定向至查询全部用户的Mapping -> allUsers()
     */
    @PostMapping(path = "/add")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/user/all";
    }

    /**
     * 异步请求
     * 查询需要更新的用户信息
     * @param userId 用户编号
     * @return 需要更新的用户对象
     */
    @GetMapping(path = "/update/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") Integer userId) {
        return userService.queryUserById(userId);
    }

    /**
     * 更新用户信息
     * @param userId 用户编号
     * @param username 用户名
     * @param password 用户密码
     * @param nickname 别名
     * @param gender 性别
     * @param accountStatus 账户状态
     * @return 重定向至查询全部用户的Mapping -> allUsers()
     */
    @PostMapping(path = "/update/{id}")
    public String updateUser(@PathVariable("id") Integer userId,
                             @RequestParam("update_username")String username,
                             @RequestParam("update_password")String password,
                             @RequestParam("update_nickname")String nickname,
                             @RequestParam("update_gender")Integer gender,
                             @RequestParam("update_accountStatus")int accountStatus) {
        User updateUserData = new User(userId, username, password, nickname, gender, accountStatus);
        userService.updateUserById(updateUserData);
        return "redirect:/admin/user/all";
    }

}
