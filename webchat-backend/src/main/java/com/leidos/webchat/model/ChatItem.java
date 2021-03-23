package com.leidos.webchat.model;


import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CHAT_ITEM")
@Getter @Setter
@NoArgsConstructor
public class ChatItem {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer chatItemId;
	
	@Column(name="MSG_ID")
	private String msgId;
	
	@Column(name="REQUEST_IND")
	private String requestInd;
	
	@Column(name="TEXT")
	private String text;
	
	@Column(name="AUTO_MESSAGE_ID")
	private String autoMsg_id;
	
	@ManyToOne
	@JoinColumn(name="CHAT_ID", nullable=false)
	private Chat chat;
	
	@Column(name="CREATED_AT")
	private ZonedDateTime createdAt;
	
	@Column(name="UPDATED_AT")
	private ZonedDateTime updatedAt;
	

}
