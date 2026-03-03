package com.gonzomonk.gonzoCards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {
    public String frontSide;
    public String backSide;
    public String description;
}
