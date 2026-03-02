package com.gonzomonk.gonzoCards.service;

import com.gonzomonk.gonzoCards.entity.Card;
import com.gonzomonk.gonzoCards.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private static final Random RANDOM = new Random();

    public Card getRandomCard() {
        long count = cardRepository.count();
        int randIndex = RANDOM.nextInt((int) count);
        Page<Card> cardPage = cardRepository.findAll(PageRequest.of(randIndex, 1));
        return cardPage.getContent().getFirst();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card card) {
        Card existingCard = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
        existingCard.setFrontSide(card.getFrontSide());
        existingCard.setBackSide(card.getBackSide());
        existingCard.setDescription(card.getDescription());
        return cardRepository.save(existingCard);
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card with id=" + id + " not found"));
        cardRepository.deleteById(id);
    }
}
