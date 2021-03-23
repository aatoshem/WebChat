package com.leidos.webchat.model;


import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CHAT")
@Getter @Setter
@NoArgsConstructor
public class Chat {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long chatId;
	
	@OneToMany(mappedBy="chat")
	private Set<ChatItem> chatItems;
	
	@JoinColumn(name="USER_ID", nullable=false)
	private Agent user;
	
	@Column(name="CREATED_AT")
	private ZonedDateTime createdAt;
	
	@Column(name="UPDATED_AT")
	private ZonedDateTime updatedAt;
	
	
}
