package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author moodright
 * @date 2021/3/21
 */

@Data
@NoArgsConstructor
public class CommentDTO extends Comment{
    /**
     * 回复者头像
     */
    private String replyerAvatar;
    /**
     * 该条评论下的子评论
     */
    private List<Comment> childComments;
}
