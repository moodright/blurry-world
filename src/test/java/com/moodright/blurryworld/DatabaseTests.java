package com.moodright.blurryworld;

import com.moodright.blurryworld.mapper.UserMapper;
import com.moodright.blurryworld.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author moodright
 * @date 2021/3/10
 */
@SpringBootTest
public class DatabaseTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    /**
     * 数据源测试
     */
    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    /**
     * 添加用户测试
     */
    @Test
    public void UserMapperAddUserTest() {
        User user = new User();
        user.setUsername("jackson@mail.com");
        user.setPassword("123456");
        user.setAccountCreateTime(new Date());
        user.setAccountLastLoginTime(new Date());
        user.setNickname("tom");
        userMapper.addUser(user);
    }

    /**
     * 查询全部用户测试
     */
    @Test
    public void UserMapperQueryAllUserTest() {
        List<User> userList = userMapper.queryAllUser();
        for(User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 根据用户名查询用户测试
     */
    @Test
    public void UserMapperQueryUserByUsernameTest() {
        User user = userMapper.queryUserByUsername("123456@ee.com");
        System.out.println(user);
    }

    @Test
    public void suggestUsernameTest() {
        List<String> usernameList = userMapper.suggestUsernameByInputString("moon");
        for(String username : usernameList) {
            System.out.println(username);
        }
    }


}
