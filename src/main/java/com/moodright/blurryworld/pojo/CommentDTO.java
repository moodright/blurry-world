package com.moodright.blurryworld.pojo;

import com.moodright.blurryworld.utils.PostPaginationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/21
 */

@Data
@NoArgsConstructor
public class CommentDTO{
    /**
     * 回复者头像（存放当前会话用户的头像）
     */
    private String replyerAvatar;

    /**
     * 分页信息
     */
    private Map<String,Integer> paginationInfo;

    /**
     * 根评论信息
     */
    private Map<String, Comment> rootComment = new HashMap<>();

    /**
     * 子评论信息
     */
    private Map<String, List<ChildComment>> childComments = new HashMap<>();

}
