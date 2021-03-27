package com.leidos.webchat.repository;

import com.leidos.webchat.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long> {
}
