package com.gonzomonk.gonzoCards.controller;

import com.gonzomonk.gonzoCards.dto.CardRequestDto;
import com.gonzomonk.gonzoCards.dto.CardResponseDto;
import com.gonzomonk.gonzoCards.entity.Card;
import com.gonzomonk.gonzoCards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping("/rand")
    public ResponseEntity<CardResponseDto> showRandCard() {
        return ResponseEntity.ok(cardService.getRandomCard());
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDto>> getAllCards(){
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(cardService.createCard(cardRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDto> updateCard(
            @PathVariable Long id,
            @RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity.ok(cardService.updateCard(id, cardRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

}
