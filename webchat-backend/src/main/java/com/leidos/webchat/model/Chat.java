package com.leidos.webchat.model;


import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Chat extends AuditInfo {
	
	@Id
	@Column
	private String chatId;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private ChatStatusEnum chatStatus;

	@OneToMany(mappedBy="chat")
	private Set<ChatMessage> chatMessages;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;

	public Chat() {
		this.chatId = UUID.randomUUID().toString();
	}
	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(Set<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}

	public ChatStatusEnum getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(ChatStatusEnum chatStatus) {
		this.chatStatus = chatStatus;
	}
}
