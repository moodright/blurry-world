package com.moodright.blurryworld.dao;

import com.moodright.blurryworld.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/2/27
 */
@Repository
public class UserDao {

    // 用户表
    private static Map<Integer, User> users = null;
    static {
        users = new HashMap<Integer, User>();
        users.put(1001, new User(1001, "amoonlight-3@foxmail.com", "123456", "moon1light"));
        users.put(1002, new User(1002, "bmoonlight-3@foxmail.com", "123456", "moon2light"));
        users.put(1003, new User(1003, "cmoonlight-3@foxmail.com", "123456", "moon3light"));
        users.put(1004, new User(1004, "dmoonlight-3@foxmail.com", "123456", "moon4light"));
        users.put(1005, new User(1005, "emoonlight-3@foxmail.com", "123456", "moon5light"));
    }

    // 数据库主键
    private static Integer initialUserId = 1006;

    // 添加一个用户
    public int addUser(User user) {
        users.put(initialUserId++, user);
        return 1;
    }
    // 根据用户编号查询用户
    public User queryUserById(Integer id) {
        return users.get(id);
    }

    // 根据用户编号删除用户
    public int deleteUserById(Integer id) {
        if(users.remove(id) != null) {
            // 删除成功
            return 1;
        }else {
            // 删除失败
            return 0;
        }
    }
}
