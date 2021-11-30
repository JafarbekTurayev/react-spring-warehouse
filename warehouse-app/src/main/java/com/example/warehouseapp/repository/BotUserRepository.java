package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotUserRepository extends JpaRepository<BotUser, Integer> {
    Optional<BotUser> findByChatIdAndActiveTrue(String chatId);
}
