package com.leidos.webchat.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Table
@NoArgsConstructor
public class Agent extends AuditInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long agentId;

    @Column
    private String agentName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "agent")
    private List<AgentRole> agentRole;

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AgentRole> getAgentRole() {
        return agentRole;
    }

    public void setAgentRole(List<AgentRole> agentRole) {
        this.agentRole = agentRole;
    }
}
