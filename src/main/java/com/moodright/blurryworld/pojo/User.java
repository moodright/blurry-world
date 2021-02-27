package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

/**
 * @author moodright
 * @date 2021/2/27
 */
@Data
@NoArgsConstructor
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
     *  默认值：“”
     */
    private String nickname;
    /**
     *  用户性别
     *  格式要求： 男：0 女：1 保密：2
     *  默认值：2
     */
    private Integer gender;
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
     *  默认值：false
     */
    private Boolean accountIsDeleted;
    /**
     *  用户头像
     *  默认值：null
     */
    private String avatar;

    public User(Integer userId,
                String username,
                String password,
                String nickname) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        // 默认值
        this.gender = 2;
        this.accountCreateTime = new Date();
        this.accountLastLoginTime = null;
        this.accountIsDeleted = false;
        this.avatar = null;
    }

    public User(Integer userId,
                String username,
                String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        // 默认值
        this.nickname = "";
        this.gender = 2;
        this.accountCreateTime = new Date();
        this.accountLastLoginTime = null;
        this.accountIsDeleted = false;
        this.avatar = null;
    }
}
