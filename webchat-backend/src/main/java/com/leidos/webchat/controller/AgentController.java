package com.leidos.webchat.controller;

import com.leidos.webchat.model.Agent;
import com.leidos.webchat.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping(value = "/agent/{id}")
    public Agent retrieveAgent(Long id) {
        return agentService.retrieveAgentWithId(id);
    }

}
