package com.moodright.blurryworld;

import com.moodright.blurryworld.mapper.UserMapper;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.utils.PaginationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/10
 */
@SpringBootTest
public class PaginationTests {

    UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    PaginationUtil paginationUtil;
    @Autowired
    public void setPaginationUtil(PaginationUtil paginationUtil) {
        this.paginationUtil = paginationUtil;
    }

    @Test
    public void queryAllUserCountTest() {
        System.out.println(userMapper.queryAllUserCount());
    }

    @Test
    public void queryUserByPaginationTest() {
        paginationUtil.setTotalCount(userMapper.queryAllUserCount());
        paginationUtil.setCurrentStartIndex(0);
        paginationUtil.setPageSize(3);
        Map<String, Integer> map = new HashMap<>();
        map.put("currentStartIndex", paginationUtil.getCurrentStartIndex());
        map.put("pageSize", paginationUtil.getPageSize());
        List<User> userList = userMapper.queryUserByPagination(map);
        for(User user : userList) {
            System.out.println(user);
        }

    }
}
