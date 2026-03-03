package com.gonzomonk.gonzoCards.repository;

import com.gonzomonk.gonzoCards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
