package com.leidos.webchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long customerId;

    @Column
    private String ipAddress;

    @Column
    private String alias;


}
