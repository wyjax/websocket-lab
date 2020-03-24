package com.wyjax.websocketlab.controller;

import com.wyjax.websocketlab.domain.ChatRequest;
import com.wyjax.websocketlab.domain.ChatResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MessageHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("start")
    public String start() {
        return "start";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/roomId")
    public ChatResponse boardCasting(ChatRequest request) throws Exception {
        ChatResponse response = new ChatResponse();
        response.setUser(request.getUser());
        response.setMessage(request.getMessage());
        response.setDatetime(formatter.format(ZonedDateTime.now()));

        return response;
    }
}
