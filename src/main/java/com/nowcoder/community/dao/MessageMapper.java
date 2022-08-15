package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
//    查询会话列表
    List<Message> selectConversations(int userId, int offset, int limit);
//    查询当前用户会话数量
    int selectConversationCount(int userId);
//    查询某个会话所包含的私信列表
    List<Message> selectLetters(String conversationId,int offset,int limit);
//    查询某个会话所包含的私信数量
    int selectLetterCount(String conversationId);
//    查询维度私信数量
    int selectLetterUnreadCount(int userId,String conversationId);
//    增加私信
    int insertMessage(Message message);
//    修改消息状态
    int updateStatus(List<Integer> ids,int status);
}
