package com.gonzomonk.gonzoCards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private String type;
    private String username;
    private List<String> roles;

    public JwtResponseDto(String token, String username, List<String> roles) {
        this(token, "Bearer", username, roles);
    }
}
