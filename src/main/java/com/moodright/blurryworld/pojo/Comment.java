package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author moodright
 * @date 2021/3/20
 */
@Data
@NoArgsConstructor
public class Comment {
    /**
     *  评论编号
     */
    private Integer commentId;
    /**
     *  评论文章编号
     */
    private Integer commentPostId;
    /**
     *  评论作者编号
     */
    private Integer commentAuthorId;
    /**
     *  父级评论编号
     */
    private Integer commentParentId;
    /**
     *  评论创建时间
     */
    private Date commentCreateTime;
    /**
     *  评论状态
     *  默认值：false 正常
     *         true  已删除
     */
    private boolean commentStatus;
    /**
     *  评论内容
     */
    private String commentContent;
    /**
     *  评论点赞数
     */
    private int commentLikes;
    /**
     *  评论点踩数
     */
    private int commentDislikes;
}
