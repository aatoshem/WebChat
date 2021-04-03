package com.example.messagingstompwebsocket;

public class Greeting {

	private String content;
	private String chatTopicToAgent;
	private String chatTopicToCustomer;

	public Greeting() {
	}

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getChatTopicToAgent() {
		return chatTopicToAgent;
	}

	public void setChatTopicToAgent(String chatTopicToAgent) {
		this.chatTopicToAgent = chatTopicToAgent;
	}

	public String getChatTopicToCustomer() {
		return chatTopicToCustomer;
	}

	public void setChatTopicToCustomer(String chatTopicToCustomer) {
		this.chatTopicToCustomer = chatTopicToCustomer;
	}
}
