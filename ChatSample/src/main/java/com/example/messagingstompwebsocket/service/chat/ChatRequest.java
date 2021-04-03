package com.example.messagingstompwebsocket.service.chat;

import java.util.Date;

public class ChatRequest {
    private String chatId;

    private String userName;

    private String agentId;

    private final Date timestamp = new Date();

    private boolean agentConnected;
    private boolean chatCompleted;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAgentConnected() {
        return agentConnected;
    }

    public void setAgentConnected(boolean agentConnected) {
        this.agentConnected = agentConnected;
    }

    public boolean isChatCompleted() {
        return chatCompleted;
    }

    public void setChatCompleted(boolean chatCompleted) {
        this.chatCompleted = chatCompleted;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
