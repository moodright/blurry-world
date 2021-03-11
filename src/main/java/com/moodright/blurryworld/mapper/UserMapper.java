package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/10
 */

@Mapper
@Repository
public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户对象
     * @return 受影响的行数
     */
    int addUser(User user);

    /**
     * Deprecated
     * 查询全部用户
     * @return 用户实体类列表
     */
    List<User> queryAllUser();

    /**
     * 根据id查询用户信息
     * @return 用户实体类
     */
    User queryUserById(@Param("userId")Integer id);

    /**
     * 根据id更新用户信息
     * @param user 用户对象
     * @return 受影响的行数
     */
    int updateUserById(User user);

    /**
     * 根据id删除用户信息
     * @param id 用户编号
     * @return 受影响的行数
     */
    int deleteUserById(@Param("userId")Integer id);

    /**
     * 查询所有用户数量
     * @return 所有用户数量
     */
    int queryAllUserCount();

    /**
     * 分页查询所有用户
     * @param paginationInfo key: currentStartIndex 当前查询起始下标
     *                       pageSize 每页查询的数量
     * @return 用户实体类列表
     */
    List<User> queryUserByPagination(Map<String, Integer> paginationInfo);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体类
     */
    User queryUserByUsername(String username);

    /**
     * 更新头像
     * @param user 封装好头像url和用户编号的实体类
     * @return 受影响的行数
     */
    int updateAvatar(User user);
}
