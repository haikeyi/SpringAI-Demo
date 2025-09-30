package com.song.ai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    @Autowired
    public ChatController(ChatClient chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
    }

    @RequestMapping("/chat")
    public String chat(String input) {
        log.info("用户说(非流式)：{}", input);
        return chatClient.prompt()
                .user(input)
                .call() // 非流式响应
                .content();
    }

    @RequestMapping(value = "/chat1", produces = "text/html;charset=utf-8")
    public Flux<String> streamChat(String input, Long chatId){
        log.info("用户说：{}", input);
        return chatClient
                .prompt()
                .user(input)
                .advisors(new Consumer<ChatClient.AdvisorSpec>() {
                    @Override
                    public void accept(ChatClient.AdvisorSpec advisorSpec) {
                        advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId);
                    }
                })
                .stream()
                .content();
    }


}
