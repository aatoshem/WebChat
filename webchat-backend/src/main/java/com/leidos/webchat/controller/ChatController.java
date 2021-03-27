package com.leidos.webchat.controller;

import com.leidos.webchat.service.ChatService;
import com.leidos.webchat.websocket.WebChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public WebChatMessage sendMessage(@Payload WebChatMessage webChatMessage) {
        //call chat service
        chatService.saveWebChatMessage(webChatMessage);
        return webChatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public WebChatMessage addUser(@Payload WebChatMessage webChatMessage,
                                  SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", webChatMessage.getSender());
        //Create Chat for username
//        chatService.CreateChat(webChatMessage);
        return webChatMessage;
    }
}
