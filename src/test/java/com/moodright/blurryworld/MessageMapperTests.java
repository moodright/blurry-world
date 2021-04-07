package com.moodright.blurryworld;

import com.moodright.blurryworld.mapper.MessageMapper;
import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author moodright
 * @date 2021/4/7
 */
@SpringBootTest
public class MessageMapperTests {

    @Autowired
    MessageService messageService;

    @Test
    void addMessageTest() {
        Message message = new Message();
        message.setMessageOwnerId(222);
        message.setMessagePostId(222);
        message.setMessageReplyerId(555);
        message.setMessageReplyerCommentId(555);
        messageService.addMessage(message);
        System.out.println("messageId =>" + message.getMessageId());
    }
}
