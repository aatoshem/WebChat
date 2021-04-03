package com.example.messagingstompwebsocket.service.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ChatServiceImpl implements  ChatService{
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private Map<String, ChatRequest> chatRequests = new HashMap<>();

    public static final String NEW_USER_REQUEST_TOPIC = "/topic/newUserRequest";

    @Override
    public String getChatTopicToAgent(String chatId) {
        return "/topic/MessageToAgent/" + chatId;
    }

    @Override
    public String getChatTopicToCustomer(String chatId) {
        return "/topic/MessageToCustomer/" + chatId;
    }

    @Override
    public String createNewChat(String userName) {
        return UUID.randomUUID().toString();
    }

    @Override
    public void broadcastNewCustomerRequest(String chatId, String userName) {
        ChatRequest request = new ChatRequest();
        request.setChatId(chatId);
        request.setUserName(userName);
        chatRequests.put(chatId, request);

        simpMessagingTemplate.convertAndSend(NEW_USER_REQUEST_TOPIC, request);
    }

    @Override
    public boolean acceptCustomerRequest(String chatId, String agentId) {
        ChatRequest request = chatRequests.get(chatId);

        if (null != chatId) {
            if (request.isAgentConnected()){
                return false;
            }
        }

        request.setAgentId(agentId);
        request.setAgentConnected( true);

        ChatMessage chatMessage = new ChatMessage(ChatMessage.MSG_TYPE_INITIATE,
                "Hello " + request.getUserName() + " my name is " + agentId + " how can I help you?");


        simpMessagingTemplate.convertAndSend(getChatTopicToCustomer(chatId),
                chatMessage);
        return true;
    }

    @Override
    public void pushCustomerMessage(String chatId, String message) {
        String topic = getChatTopicToAgent(chatId);

        simpMessagingTemplate.convertAndSend(topic, new ChatMessage(message));
    }

    @Override
    public void pushAgentMessage(String chatId, String message) {
        String topic = getChatTopicToCustomer(chatId);

        simpMessagingTemplate.convertAndSend(topic, new ChatMessage(message));
    }

    @Override
    public List<ChatRequest> openChatRequests(){
        return chatRequests.values().stream().filter(e -> !e.isAgentConnected()).collect(toList());
    }

    @Override
    public ChatRequest getChatRequest(String chatId){
        return chatRequests.get(chatId);
    }
}
