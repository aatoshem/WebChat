package com.leidos.webchat.controller;

import com.leidos.webchat.model.Agent;
import com.leidos.webchat.model.Chat;
import com.leidos.webchat.service.AgentService;
import com.leidos.webchat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController {

    private AgentService agentService;
    private ChatService chatService;

    @Autowired
    public AgentController(AgentService agentService, ChatService chatService) {
        this.agentService = agentService;
        this.chatService = chatService;
    }

    @GetMapping(value = "/agent/retrieve")
    public Agent retrieveAgent(@AuthenticationPrincipal OAuth2User oauth2User) {
        return agentService.retrieveAgentWithId(oauth2User.getAttribute("name"));
    }

    @GetMapping(value = "/customerRequests")
    public List<Chat> getCustomerRequests() {
        return chatService.openChatRequests();
    }

}
