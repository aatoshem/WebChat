package com.leidos.webchat.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
public class Chat  extends AuditInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long chatId;
	
	@OneToMany(mappedBy="chat")
	private Set<ChatMessage> chatItems;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
}
