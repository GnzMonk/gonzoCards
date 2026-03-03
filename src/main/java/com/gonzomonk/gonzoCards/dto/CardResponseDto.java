package com.gonzomonk.gonzoCards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {
    public Long id;
    public String frontSide;
    public String backSide;
    public String description;

}
