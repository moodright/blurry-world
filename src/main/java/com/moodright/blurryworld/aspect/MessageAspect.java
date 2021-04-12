package com.moodright.blurryworld.aspect;

import com.moodright.blurryworld.pojo.ChildComment;
import com.moodright.blurryworld.pojo.Comment;
import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.service.MessageService;
import com.moodright.blurryworld.service.PostService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息通知切面
 * @author moodright
 * @date 2021/4/7
 */
@Aspect
@Component
public class MessageAspect {
    MessageService messageService;
    PostService postService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * 添加根评论消息通知
     * 匹配 {@link com.moodright.blurryworld.service.CommentService#addComment(Comment)} 切入点
     * @param comment 传递给切入点方法的封装好的评论实体类
     * @param returnValue 切入点方法执行后的返回值, 判断评论添加是否成功字段
     */
    @AfterReturning(value = "execution(int com.moodright.blurryworld.service.CommentService.addComment(..)) &&" +
                    "args(comment)",
                    argNames = "comment, returnValue",
                    returning = "returnValue")
    public void addRootCommentMessageAdvice(Comment comment, int returnValue) {
        if(returnValue > 0) {
            // 封装消息对象
            Message message = new Message();
            message.setMessagePostId(comment.getCommentPostId());
            message.setMessageReplyerId(comment.getCommentAuthorId());
            message.setMessageReplyerCommentId(comment.getCommentId());
            // 封装该篇文章的作者编号
            message.setMessageOwnerId(postService.queryAuthorIdByPostId(comment.getCommentPostId()));
            // 消息通知
            if(!message.getMessageOwnerId().equals(message.getMessageReplyerId())) {
                // 不添加自己的消息通知
                messageService.addMessage(message);
            }
        }
    }

    /**
     * 添加子评论消息通知
     * 与根评论消息通知的区别是：根评论通知的为文章的作者，而子评论通知的为评论作者
     * 匹配 {@link com.moodright.blurryworld.service.CommentService#addChildComment(ChildComment)} 切入点
     * @param childComment 传递给切入点方法的封装好的子评论实体类
     * @param returnValue 切入点方法执行后的返回值, 判断评论添加是否成功字段
     */
    @AfterReturning(value = "execution(int com.moodright.blurryworld.service.CommentService.addChildComment(*)) &&" +
                    "args(childComment)",
                    argNames = "childComment, returnValue",
                    returning = "returnValue")
    public void addChildCommentMessageAdvice(ChildComment childComment, int returnValue) {
        if(returnValue > 0) {
            Message message = new Message();
            // 封装消息对象
            message.setMessageReplyerId(childComment.getCommentAuthorId());
            message.setMessageReplyerCommentId(childComment.getCommentId());
            message.setMessageOwnerId(childComment.getParentCommentAuthorId());
            // 消息通知
            if(!message.getMessageOwnerId().equals(message.getMessageReplyerId())) {
                // 不添加自己的消息通知判断
                messageService.addMessage(message);
            }
        }
    }

}
