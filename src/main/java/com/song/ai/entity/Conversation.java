package com.song.ai.entity;

import lombok.Data;
import org.springframework.ai.chat.messages.Message;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Conversation {
    private String sessionId;          // 会话唯一标识
    private List<Message> messages;    // 消息列表
    private LocalDateTime createTime;  // 创建时间
    private LocalDateTime updateTime;  // 更新时间
}

