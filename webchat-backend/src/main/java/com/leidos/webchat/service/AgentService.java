package com.leidos.webchat.service;

import com.leidos.webchat.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private com.leidos.webchat.repository.AgentRepo AgentRepo;

    public Agent retrieveAgentWithId(Long id) {
        Agent agent = AgentRepo.getOne(id);
        return agent;
    }
}
