package com.moodright.blurryworld.service;

import com.moodright.blurryworld.mapper.MessageMapper;
import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.pojo.messagecenter.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author moodright
 * @date 2021/4/7
 */
@Service
public class MessageService implements MessageMapper {

    MessageMapper messageMapper;

    @Autowired
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    /**
     * 添加消息
     *
     * @param message 通知实体类
     * @return 受影响的行数
     */
    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    /**
     * 根据被通知人编号查询消息
     *
     * @param messageOwnerId 被通知人编号
     * @return 消息列表
     */
    @Override
    public List<MessageDTO> queryMessageByMessageOwnerId(Integer messageOwnerId) {
        return messageMapper.queryMessageByMessageOwnerId(messageOwnerId);
    }

    /**
     * 删除消息
     *
     * @param messageId 消息编号
     * @return 受影响的行数
     */
    @Override
    public int deleteMessageByMessageId(Integer messageId) {
        return messageMapper.deleteMessageByMessageId(messageId);
    }

    /**
     * 更新消息
     *
     * @param messageId 消息编号
     * @return 受影响的行数
     */
    @Override
    public int updateMessageByMessageId(Integer messageId) {
        return messageMapper.updateMessageByMessageId(messageId);
    }
}
