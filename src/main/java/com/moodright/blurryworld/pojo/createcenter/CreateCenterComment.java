package com.moodright.blurryworld.pojo.createcenter;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.Post;
import com.moodright.blurryworld.pojo.User;
import lombok.Data;

/**
 * 创作中心评论
 * @author moodright
 * @date 2021/3/28
 */

@Data
public class CreateCenterComment {
    /**
     * 评论信息
     */
    private ChildComment comment;
    /**
     * 评论关联的文章信息
     */
    private Post post;
    /**
     * 发表评论的用户信息
     */
    private User user;
}
