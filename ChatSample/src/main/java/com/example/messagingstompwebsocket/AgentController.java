package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.service.chat.ChatMessage;
import com.example.messagingstompwebsocket.service.chat.ChatRequest;
import com.example.messagingstompwebsocket.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController {

    @Value("${base.url:localhost}")
    private String baseUrl;

    @Autowired
    private ChatService chatService;

    @GetMapping (value="/baseUrl")
    public String getBaseUrl(){
        return baseUrl;
    }

    @GetMapping(value = "/sendMessageToAgent/{chatId}{message}")
    public void sendMessageToAgent(@PathVariable("chatId") String chatId,
                                   @PathVariable("message") String message)
    {
        chatService.broadcastNewCustomerRequest(chatId,message);
    }

    @GetMapping(value = "/customerRequests")
    public List<ChatRequest> getCustomerRequests()
    {
        return chatService.openChatRequests();
    }

    @GetMapping(value = "/grab/{chatId}")
    public void grab(@PathVariable("chatId") String chatId)
    {
        String topic = chatService.getChatTopicToCustomer(chatId);


        chatService.acceptCustomerRequest(chatId, "1234");
    }
}
