package com.leidos.webchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
public class AgentRole {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long AgentRoleId;

    @ManyToOne
    @JoinColumn(name ="agent_id", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
