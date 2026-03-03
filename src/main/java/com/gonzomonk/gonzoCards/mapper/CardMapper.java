package com.gonzomonk.gonzoCards.mapper;

import com.gonzomonk.gonzoCards.dto.CardRequestDto;
import com.gonzomonk.gonzoCards.dto.CardResponseDto;
import com.gonzomonk.gonzoCards.entity.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toEntity(CardRequestDto cardRequestDto);

    CardResponseDto toDto(Card card);

    List<CardResponseDto> toDtoList(List<Card> cardList);
}
