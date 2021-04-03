package com.example.messagingstompwebsocket.service.chat;

import java.util.List;

public interface ChatService {

    String getChatTopicToAgent(String chatId);

    String getChatTopicToCustomer(String chatId);

    String createNewChat(String userName);

    void broadcastNewCustomerRequest(String chatId, String userName);

    boolean acceptCustomerRequest(String chatId, String agentId);

    void pushCustomerMessage(String chatId, String message);

    void pushAgentMessage(String chatId, String message);

    List<ChatRequest> openChatRequests();


    ChatRequest getChatRequest(String chatId);
}
