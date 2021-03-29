package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author moodright
 * @date 2021/3/23
 */
@Data
@NoArgsConstructor
public class ChildComment extends Comment{
    /**
     * 回复的评论作者编号
     */
    private Integer parentCommentAuthorId;
    /**
     * 回复的评论作者用户名
     */
    private String parentCommentAuthorUsername;
    /**
     * 回复的评论编号
     */
    private Integer parentCommentId;
}
