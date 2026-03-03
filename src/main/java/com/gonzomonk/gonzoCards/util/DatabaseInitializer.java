package com.gonzomonk.gonzoCards.util;

import com.gonzomonk.gonzoCards.entity.Role;
import com.gonzomonk.gonzoCards.entity.User;
import com.gonzomonk.gonzoCards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByLogin("admin").isEmpty()) {
            User admin = User.builder()
                    .login("admin")
                    .password(passwordEncoder.encode("admin_pass")) // Хешируем на лету
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Админ создан: admin / admin_pass");
        }

        if (userRepository.findByLogin("user").isEmpty()) {
            User user = User.builder()
                    .login("user")
                    .password(passwordEncoder.encode("user_pass"))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            System.out.println("Пользователь создан: user / user_pass");
        }
    }
}