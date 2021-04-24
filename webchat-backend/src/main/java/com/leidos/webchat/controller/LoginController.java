package com.leidos.webchat.controller;

import com.leidos.webchat.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class LoginController {

    private AgentService agentService;

    @Autowired
    public LoginController(AgentService agentService) {
        this.agentService = agentService;
    }

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }

    @RequestMapping("/agent")
    public String agent(Model model, @AuthenticationPrincipal OAuth2User oauth2User) {
        agentService.saveAgentOnFirstLogin(oauth2User);
        return "agent";
    }

}
