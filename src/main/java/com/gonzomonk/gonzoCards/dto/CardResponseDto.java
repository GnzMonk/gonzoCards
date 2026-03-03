package com.gonzomonk.gonzoCards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String frontSide;
    private String backSide;
    private String description;

}
