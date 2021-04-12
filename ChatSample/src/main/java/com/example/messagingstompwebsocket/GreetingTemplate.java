package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class GreetingTemplate {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(){
        String content = "{'content':'hello again}";
        Greeting greeting = new Greeting("Hello again!");
        simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
    }
}
