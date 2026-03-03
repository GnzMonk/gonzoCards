package com.gonzomonk.gonzoCards.controller;

import com.gonzomonk.gonzoCards.dto.JwtResponseDto;
import com.gonzomonk.gonzoCards.dto.LoginRequestDto;
import com.gonzomonk.gonzoCards.entity.User;
import com.gonzomonk.gonzoCards.repository.UserRepository;
import com.gonzomonk.gonzoCards.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody LoginRequestDto request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new JwtResponseDto(token, user.getUsername(), List.of(user.getRole().name()));
    }
}