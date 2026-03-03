package com.gonzomonk.gonzoCards.service;

import com.gonzomonk.gonzoCards.dto.CardRequestDto;
import com.gonzomonk.gonzoCards.dto.CardResponseDto;
import com.gonzomonk.gonzoCards.entity.Card;
import com.gonzomonk.gonzoCards.mapper.CardMapper;
import com.gonzomonk.gonzoCards.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private static final Random RANDOM = new Random();

    public CardResponseDto getRandomCard() {
        long count = cardRepository.count();
        if (count == 0) {
            throw new EntityNotFoundException("No card found");
        }
        int randIndex = RANDOM.nextInt((int) count);
        Page<Card> cardPage = cardRepository.findAll(PageRequest.of(randIndex, 1));
        return cardMapper.toDto(cardPage.getContent().getFirst());
    }
    
    public List<CardResponseDto> getAllCards() {
        return cardMapper.toDtoList(cardRepository.findAll());
    }

    public CardResponseDto getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
        return cardMapper.toDto(card);
    }

    public CardResponseDto createCard(CardRequestDto cardRequestDto) {
        Card savedCard = cardRepository.save(cardMapper.toEntity(cardRequestDto));
        return cardMapper.toDto(savedCard);
    }

    @Transactional
    public CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto) {
        Card existingCard = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
        existingCard.setFrontSide(cardRequestDto.getFrontSide());
        existingCard.setBackSide(cardRequestDto.getBackSide());
        existingCard.setDescription(cardRequestDto.getDescription());
        return cardMapper.toDto(cardRepository.save(existingCard));
    }

    @Transactional
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
        cardRepository.deleteById(id);
    }
}
