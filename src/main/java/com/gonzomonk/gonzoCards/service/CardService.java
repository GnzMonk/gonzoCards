package com.gonzomonk.gonzoCards.service;

import com.gonzomonk.gonzoCards.entity.Card;
import com.gonzomonk.gonzoCards.repository.CardRepository;
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

    public Card getRandomCard() {
        long count = cardRepository.count();
        int randIndex = new Random().nextInt((int) count);
        Page<Card> cardPage = cardRepository.findAll(PageRequest.of(randIndex, 1));
        return cardPage.getContent().getFirst();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card card) {
        if (cardRepository.existsById(id)) {
            card.setId(id);
            return cardRepository.save(card);
        }
        else {
            return cardRepository.save(card);
        }
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
