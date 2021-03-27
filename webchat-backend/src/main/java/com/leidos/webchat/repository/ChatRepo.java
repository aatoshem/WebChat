package com.leidos.webchat.repository;

import com.leidos.webchat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
}
