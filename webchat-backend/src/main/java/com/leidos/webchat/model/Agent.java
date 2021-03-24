package com.leidos.webchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
public class Agent {
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
    
    @OneToMany(mappedBy="agent")
	private Set<Chat> chats;

}
