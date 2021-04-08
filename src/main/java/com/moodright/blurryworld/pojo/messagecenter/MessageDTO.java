package com.moodright.blurryworld.pojo.messagecenter;

import com.moodright.blurryworld.pojo.*;
import lombok.Data;

/**
 * 消息传输对象
 * @author moodright
 * @date 2021/4/7
 */

@Data
public class MessageDTO extends Message {
    /**
     * 通知的用户实体类
     */
    private User replyer;
    /**
     * 通知的评论实体类
     */
    private ChildComment comment;
    /**
     * 被通知人的评论实体类（内容）
     */
    private Comment myComment;
    /**
     * 通知的文章实体类
     */
    private Post post;
}
