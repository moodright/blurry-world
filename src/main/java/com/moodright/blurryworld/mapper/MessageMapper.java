package com.moodright.blurryworld.mapper;

import com.moodright.blurryworld.pojo.Message;
import com.moodright.blurryworld.pojo.messagecenter.MessageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author moodright
 * @date 2021/4/7
 */

@Mapper
@Repository
public interface MessageMapper {

    /**
     * 添加消息
     * @param message 通知实体类
     * @return 受影响的行数
     */
    int addMessage(Message message);

    /**
     * 根据被通知人编号查询消息
     * @param messageOwnerId 被通知人编号
     * @return 消息列表
     */
    List<MessageDTO> queryMessageByMessageOwnerId(@Param("messageOwnerId")Integer messageOwnerId);

    /**
     * 删除消息
     * @param messageId 消息编号
     * @return 受影响的行数
     */
    int deleteMessageByMessageId(@Param("messageId")Integer messageId);

    /**
     * 更新消息
     * @param messageId 消息编号
     * @return 受影响的行数
     */
    int updateMessageByMessageId(@Param("messageId")Integer messageId);
}
