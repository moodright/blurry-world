package com.moodright.blurryworld.pojo;

import lombok.Data;
import lombok.Getter;

/**
 * @author moodright
 * @date 2021/4/7
 */

@Data
public class Message {
    /**
     * 消息编号
     */
    private Integer messageId;
    /**
     * 消息所属的文章编号
     */
    private Integer messagePostId;
    /**
     * 消息被通知人编号
     */
    private Integer messageOwnerId;
    /**
     * 消息通知人编号
     */
    private Integer messageReplyerId;
    /**
     * 消息通知人的评论编号
     */
    private Integer messageReplyerCommentId;
    /**
     * 消息状态
     * 默认值：false 未查看
     *       true 已查看
     */
    private boolean messageStatus;
}
