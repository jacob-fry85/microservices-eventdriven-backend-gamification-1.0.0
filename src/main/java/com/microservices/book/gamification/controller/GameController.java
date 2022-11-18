package com.microservices.book.gamification.controller;

import com.microservices.book.gamification.challenge.ChallengeSolvedEvent;
import com.microservices.book.gamification.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    @Autowired
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void postResult(@RequestBody ChallengeSolvedEvent dto) {
        gameService.newAttemptForUser(dto);
    }
}
