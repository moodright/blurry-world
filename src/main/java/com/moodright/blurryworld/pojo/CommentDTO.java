package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author moodright
 * @date 2021/3/21
 */

@Data
@NoArgsConstructor
public class CommentDTO extends Comment{
    /**
     * 回复者头像（存放当前会话用户的头像）
     */
    private String replyerAvatar;
    /**
     * 该条评论下的子评论
     */
    private List<ChildComment> childComments;
}
