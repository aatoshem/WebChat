package com.leidos.webchat.service;

import com.leidos.webchat.model.Agent;
import com.leidos.webchat.repository.AgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private AgentRepo agentRepo;

    public Agent retrieveAgentWithId(String name) {
        Agent agent = agentRepo.findByAgentName(name);
        return agent;
    }

    public void saveAgentOnFirstLogin(OAuth2User oauth2User) {
        String name = oauth2User.getAttribute("name");
        Agent agent = agentRepo.findByAgentName(name);
        if(agent == null){
            agent = new Agent();
            agent.setAgentName(name);
            agentRepo.save(agent);
        }
    }
}
