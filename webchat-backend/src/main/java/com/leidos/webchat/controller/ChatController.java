package com.leidos.webchat.controller;

import com.leidos.webchat.service.ChatService;
import com.leidos.webchat.websocket.WebChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private ChatService chatService;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/sendMessage")
    public WebChatMessage sendMessage(@Payload WebChatMessage webChatMessage) {
        chatService.saveWebChatMessage(webChatMessage);
        messagingTemplate.convertAndSend("/topic/private/chat/" + webChatMessage.getChatId().toString(), webChatMessage);
        return webChatMessage;
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/public")
    public WebChatMessage addUser(@Payload WebChatMessage webChatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", webChatMessage.getSender());
        //Create Chat for username
        chatService.CreateChat(webChatMessage);
        //send to agent
        messagingTemplate.convertAndSend("/topic/customerChats", webChatMessage);
        //send to user
        return webChatMessage;
    }


}
