package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.pojo.messagecenter.MessageDTO;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论通知控制器
 *
 * @author moodright
 * @date 2021/3/31
 */
@Controller
@RequestMapping("message")
public class MessageController {
    MessageService messageService;
    CommentService commentService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("reply")
    public String replyIndex(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        // 查询所有通知消息
        List<MessageDTO> messageDTOS = messageService.queryMessageByMessageOwnerId(user.getUserId());
        model.addAttribute("messages", messageDTOS);
        return "/user/advice-center/reply-management";
    }
}
