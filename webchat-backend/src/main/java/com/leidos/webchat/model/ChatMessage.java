package com.leidos.webchat.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
public class ChatMessage extends AuditInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long chatMessageId;
	
	@Column
	private String requestInd;
	
	@Column
	private String message;
	
	@Column
	private int autoMessageId;

	@ManyToOne
	@JoinColumn(name = "agent_id", nullable = false)
	private Agent agent;

	@ManyToOne
	@JoinColumn(name="CHAT_ID", nullable=false)
	private Chat chat;

	public Long getChatMessageId() {
		return chatMessageId;
	}

	public void setChatMessageId(Long chatMessageId) {
		this.chatMessageId = chatMessageId;
	}

	public String getRequestInd() {
		return requestInd;
	}

	public void setRequestInd(String requestInd) {
		this.requestInd = requestInd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAutoMessageId() {
		return autoMessageId;
	}

	public void setAutoMessageId(int autoMessageId) {
		this.autoMessageId = autoMessageId;
	}
}
