package com.moodright.blurryworld;

import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.pojo.messagecenter.MessageDTO;
import com.moodright.blurryworld.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void queryMessageDTOTest() {
        List<MessageDTO> messages = messageService.queryMessageByMessageOwnerId(1001);
        for (MessageDTO messageDTO : messages) {
            System.out.println(messageDTO.isMessageStatus());
//            System.out.println(messageDTO.getMessageId());
//            System.out.println(messageDTO.getReplyer());
//            System.out.println(messageDTO.getComment().getCommentContent());
//            if(messageDTO.getMyComment() != null) {
//                System.out.println(messageDTO.getMyComment().getCommentContent());
//            }
//            System.out.println(messageDTO.getPost());
        }
    }

    @Test
    void queryMessageCountByOwnerIdTest() {
        int i = messageService.queryMessageCountByOwnerId(1011101);
        System.out.println(i);
    }

}
