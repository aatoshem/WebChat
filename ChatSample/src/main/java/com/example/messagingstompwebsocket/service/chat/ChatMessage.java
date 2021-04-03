package com.example.messagingstompwebsocket.service.chat;

import java.util.Date;

public class ChatMessage {

    public static final String MSG_TYPE_INITIATE="I";
    public static final String MSG_TYPE_CHAT="C";
    public static final String MSG_TYPE_TERMINATE="T";


    private String messageType;
    private String message;
    private Date timestamp = new Date();

    public ChatMessage(String messageType, String message){
        this.message = message;
        this.messageType = messageType;
        this.timestamp = new Date();
    }

    public ChatMessage(String message){
        this.message = message;
        this.messageType = MSG_TYPE_CHAT;
        this.timestamp = new Date();
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
