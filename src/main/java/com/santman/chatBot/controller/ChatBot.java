package com.santman.chatBot.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping("/api")
public class ChatBot {
    private  final ChatClient chatClient;
    public ChatBot(ChatClient.Builder chatClient){
        this.chatClient=chatClient.build();
    }
    @GetMapping("/chat")
    Flux<String> chat(@RequestParam("query") String query){
        return this.chatClient.prompt(query).stream().content();
    }
}
