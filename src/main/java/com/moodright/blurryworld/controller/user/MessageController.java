package com.moodright.blurryworld.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评论通知控制器
 *
 * @author moodright
 * @date 2021/3/31
 */
@Controller
@RequestMapping("message")
public class MessageController {

    @GetMapping("reply")
    public String replyIndex() {
        return "/user/advice-center/reply-management";
    }
}
