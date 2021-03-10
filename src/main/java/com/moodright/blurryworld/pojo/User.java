package com.moodright.blurryworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * 用户实体类
 * @author moodright
 * @date 2021/2/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     *  用户编号
     */
    private Integer userId;
    /**
     *  用户名
     *  格式要求：邮箱
     */
    private String username;
    /**
     *  用户密码
     */
    private String password;
    /**
     *  用户别名
     */
    private String nickname;
    /**
     *  用户性别
     *  格式要求： 男：0 女：1 保密：2
     *  默认值：2
     */
    private int gender = 2;
    /**
     *  账号创建时间
     *  格式要求： Timestamp
     */
    private Date accountCreateTime;
    /**
     *  账户上次登录时间
     *  格式要求：Timestamp
     */
    private Date accountLastLoginTime;
    /**
     *  账户状态
     *  正常: 0 已注销：1
     *  默认值：0
     */
    private int accountStatus;
    /**
     *  用户头像
     *  默认值：null
     */
    private String avatar;


    public User(Integer userId, String username, String password, String nickname, int gender, int accountStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.accountStatus = accountStatus;
    }
}
