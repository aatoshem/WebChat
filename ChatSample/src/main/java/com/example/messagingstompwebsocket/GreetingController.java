package com.example.messagingstompwebsocket;

import com.example.messagingstompwebsocket.service.chat.ChatRequest;
import com.example.messagingstompwebsocket.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class GreetingController {

	@Autowired
	private ChatService chatService;


	@MessageMapping("/hello1/{user}")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message,  @DestinationVariable String user) throws Exception {

		String chatId = chatService.createNewChat(user);

		Thread.sleep(1000); // simulated delay

		chatService.broadcastNewCustomerRequest(chatId, message.getName());
		Greeting greeting = new Greeting("Hello " +HtmlUtils.htmlEscape(message.getName())
				+ " Please stand by while we connect you with an agent. Your chat id is: " +chatId);
		greeting.setChatTopicToAgent(chatService.getChatTopicToAgent(chatId));
		greeting.setChatTopicToCustomer(chatService.getChatTopicToCustomer(chatId));

		return greeting;
	}

}
