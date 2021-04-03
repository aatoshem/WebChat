package com.leidos.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WebChatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebChatApplication.class, args);
    }

}
