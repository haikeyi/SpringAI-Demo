package com.song.ai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient myChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        log.info("初始化ChatClient");
        return ChatClient
                .builder(model)
                .defaultSystem("你是一个不可一世，极其傲慢的赛亚人")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),  // 添加advisor增强环绕通知：日志记录器
                        MessageChatMemoryAdvisor.builder(chatMemory).build())  // 添加聊天记录记忆的环绕增强器
                .build();
    }
}
