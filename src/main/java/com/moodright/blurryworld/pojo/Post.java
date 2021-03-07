package com.moodright.blurryworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Time;
import java.util.Date;

/**
 * 文章实体类
 * @author moodright
 * @date 2021/3/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    /**
     * 文章编号
     */
    private Integer postId;
    /**
     * 作者编号（用户编号）
     */
    private Integer authorId;
    /**
     * 文章标题
     */
    private String postTitle;
    /**
     * 文章别名
     */
    private String postAlias;
    /**
     * 文章摘要
     */
    private String postSummary;
    /**
     * 文章内容
     */
    private String postContent;
    /**
     * 文章封面图
     * 默认值：null
     */
    private String postCoverPicture;
    /**
     * 文章浏览次数
     */
    private Integer postViewCount;
    /**
     * 文章评论次数
     */
    private Integer postCommentCount;
    /**
     * 文章点赞次数
     */
    private Integer postLikeCount;
    /**
     * 文章创建时间
     * 格式要求：Timestamp
     */
    private Date postCreateTime;
    /**
     * 文章上次更新时间
     * 格式要求：Timestamp
     */
    private Date postLastUpdateTime;
    /**
     * 文章定时发布时间
     * 格式要求：Timestamp
     */
    private Date postReleaseTime;
    /**
     * 文章状态
     * 正常：false 已删除：true
     * 默认值：false
     */
    private Boolean postIsDeleted;

}
