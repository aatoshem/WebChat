package com.leidos.webchat.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
public class Chat extends AuditInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long chatId;
	
	@OneToMany(mappedBy="chat")
	private Set<ChatMessage> chatItems;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<ChatMessage> getChatItems() {
		return chatItems;
	}

	public void setChatItems(Set<ChatMessage> chatItems) {
		this.chatItems = chatItems;
	}
}
