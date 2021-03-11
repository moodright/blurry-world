package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.UserMapper;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/10
 */

@Service
public class UserService implements UserMapper {

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户
     * @param user 用户对象
     * @return 数据库受影响的行数
     */
    @Override
    public int addUser(User user) {
        //账户时间初始化
        user.setAccountCreateTime(new Date());
        user.setAccountLastLoginTime(new Date());
        return userMapper.addUser(user);
    }

    /**
     * 查询全部用户
     * @return 用户实体类列表
     */
    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户编号
     * @return 对应用户编号的用户对象
     */
    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    /**
     * 根据id更新用户信息
     * @param user 用户对象
     * @return 受影响的行数
     */
    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    /**
     * 根据id删除用户信息
     * @param id 用户编号
     * @return 受影响的行数
     */
    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    /**
     * 查询所有用户数量
     * @return 所有用户数量
     */
    @Override
    public int queryAllUserCount() {
        return userMapper.queryAllUserCount();
    }

    /**
     * 分页查询用户
     * @param limitInfo key: currentStartIndex 当前查询起始下标
     *                       pageSize 每页查询的数量
     * @return
     */
    @Override
    public List<User> queryUserByPagination(Map<String, Integer> limitInfo) {
        return userMapper.queryUserByPagination(limitInfo);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体类
     */
    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    /**
     * 更新头像
     * @param user 封装好头像url和用户编号的实体类
     * @return 受影响的行数
     */
    @Override
    public int updateAvatar(User user) {
        return userMapper.updateAvatar(user);
    }
}
