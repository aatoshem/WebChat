package com.leidos.webchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

}
