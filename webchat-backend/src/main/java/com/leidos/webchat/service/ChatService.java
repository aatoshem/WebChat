package com.leidos.webchat.service;

import com.leidos.webchat.model.Chat;
import com.leidos.webchat.model.ChatMessage;
import com.leidos.webchat.model.Customer;
import com.leidos.webchat.repository.ChatMessageRepo;
import com.leidos.webchat.repository.ChatRepo;
import com.leidos.webchat.repository.CustomerRepo;
import com.leidos.webchat.websocket.WebChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public void CreateChat(WebChatMessage c) {
        Customer customer = new Customer();
        customer.setAlias(c.getSender());

        customerRepo.save(customer);

        Chat chat = new Chat();
        chat.setCustomer(customer);

        chatRepo.save(chat);
        c.setChatId(chat.getChatId());
    }

    public void saveWebChatMessage(WebChatMessage wcMessage){
        Optional<Chat> chat = chatRepo.findById(wcMessage.getChatId());

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(wcMessage.getContent());
        chatMessage.setRequestInd("C");
        chatMessage.setAutoMessageId(0);

//        chatMessageRepo.save(chatMessage);

        chat.ifPresent(c -> {
            c.getChatItems().add(chatMessage);
            chatRepo.save(c);
        });
    }

}
