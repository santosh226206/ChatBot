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
    public ChatBot(ChatClient chatClient){
        this.chatClient=chatClient;
    }
    @GetMapping("/chat")
    Flux<String> chat(@RequestParam("query") String query){
        String systemText = """
    You are a professional garment shopkeeper. 
    Expertise: Clothing, fabrics, and fashion only.
    Constraint: If asked anything outside of garments, respond with: 
    'I only deal in clothes, my friend! How can I help with your wardrobe?'
    and answer strictly queries related garments only for other queries give regret message
    i dont want you to deviate from garments related issues , anything other than that should be ignored.
    And though i have not provided the garmrntas which i have in shop so for that you answer generally regading garments , such that user gets 
    smooth experence as he is talking to shopkeeper.
    """;
//        return this.chatClient.prompt().system(systemText).user(query).stream().content();
        return this.chatClient.prompt().user(query).stream().content();
    }
}
