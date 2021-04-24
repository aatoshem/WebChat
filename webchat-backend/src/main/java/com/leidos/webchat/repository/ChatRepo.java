package com.leidos.webchat.repository;

import com.leidos.webchat.model.Chat;
import com.leidos.webchat.model.ChatStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, String> {

    @Query("SELECT c FROM Chat c WHERE c.chatStatus = ?1")
    List<Chat> findByChatStatus(ChatStatusEnum ChatStatusDesc);
}
