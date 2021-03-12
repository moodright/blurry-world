package com.moodright.blurryworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户个人信息实体类
 * @author moodright
 * @date 2021/3/12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户签名
     */
    private String  signature;
    /**
     * 用户生日
     */
    private Date birthday;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 用户联系邮箱
     */
    private String email;
    /**
     * 用户个人网站链接
     */
    private String websiteLink;
    /**
     * 用户github链接
     */
    private String githubLink;
    /**
     * 用户微博链接
     */
    private String weiboLink;
    /**
     * 用户文章总数
     */
    private Integer postCount;
    /**
     * 用户动态总数
     */
    private Integer momentCount;
    /**
     * 用户关注数
     */
    private Integer followCount;
    /**
     * 用户粉丝数
     */
    private Integer followerCount;
}
