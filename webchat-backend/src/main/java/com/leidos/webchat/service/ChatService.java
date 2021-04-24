package com.leidos.webchat.service;

import com.leidos.webchat.model.Chat;
import com.leidos.webchat.model.ChatMessage;
import com.leidos.webchat.model.Customer;
import com.leidos.webchat.repository.ChatMessageRepo;
import com.leidos.webchat.repository.ChatRepo;
import com.leidos.webchat.repository.CustomerRepo;
import com.leidos.webchat.model.ChatStatusEnum;
import com.leidos.webchat.websocket.WebChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private ChatRepo chatRepo;
    private ChatMessageRepo chatMessageRepo;
    private CustomerRepo customerRepo;

    @Autowired
    public ChatService(ChatRepo chatRepo, ChatMessageRepo chatMessageRepo, CustomerRepo customerRepo) {
        this.chatRepo = chatRepo;
        this.chatMessageRepo = chatMessageRepo;
        this.customerRepo = customerRepo;
    }

    @Transactional
    public void CreateChat(WebChatMessage webChatMessage) {
        Customer customer = new Customer();
        customer.setAlias(webChatMessage.getSender());

        customerRepo.save(customer);

        Chat chat = new Chat();
        chat.setCustomer(customer);
        chat.setChatStatus(ChatStatusEnum.OPEN);

        chatRepo.save(chat);
        webChatMessage.setChatId(chat.getChatId());
    }

    @Transactional
    public void saveWebChatMessage(WebChatMessage wcMessage){
        Optional<Chat> chat = chatRepo.findById(wcMessage.getChatId());

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(wcMessage.getContent());
        chatMessage.setRequestInd("C");
        chatMessage.setAutoMessageId(0);

        chat.ifPresent(c -> {
            chatMessage.setChat(c);
            chatMessageRepo.save(chatMessage);
        });
    }

    public List<Chat> openChatRequests(){
        return chatRepo.findByChatStatus(ChatStatusEnum.OPEN);
    }

}
