package com.moodright.blurryworld.controller.user;

import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.pojo.User;
import com.moodright.blurryworld.pojo.messagecenter.MessageDTO;
import com.moodright.blurryworld.service.CommentService;
import com.moodright.blurryworld.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 根据用户编号查询消息
     * @param session 获取用户编号
     * @return 消息中心模板
     */
    @GetMapping("reply")
    public String replyIndex(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        // 查询所有通知消息
        List<MessageDTO> messageDTOS = messageService.queryMessageByMessageOwnerId(user.getUserId());
        model.addAttribute("messages", messageDTOS);
        // 更新所有消息为已读状态
        messageService.updateMessageByOwnerId(user.getUserId());
        return "/user/advice-center/reply-management";
    }

    /**
     * 根据用户编号查询消息数量
     * @param session 获取用户编号
     * @return 消息数量
     */
    @GetMapping("reply/count")
    @ResponseBody
    public int queryUnreadMessageCount(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return messageService.queryMessageCountByOwnerId(user.getUserId());
    }

    /**
     * 根据消息编号删除评论
     * @param messageId  消息编号
     * @return 受影响的行数
     */
    @PostMapping("delete/{id}")
    @ResponseBody
    public int deleteMessageByMessageId(@PathVariable("id")Integer messageId) {
        return messageService.deleteMessageByMessageId(messageId);
    }

}
