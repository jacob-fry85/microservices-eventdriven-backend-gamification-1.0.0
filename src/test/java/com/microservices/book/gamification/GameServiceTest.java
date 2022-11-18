package com.microservices.book.gamification;

import com.microservices.book.gamification.challenge.ChallengeSolvedEvent;
import com.microservices.book.gamification.game.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    private GameService gameService;

    @Test
    public void correctAttemptTest() {
        ChallengeSolvedEvent challengeSolvedDTO = new ChallengeSolvedEvent(null, true, 2, 2, 1, "john");

        if(challengeSolvedDTO.isCorrect()) {
            System.out.println("correct");
        }
    }

    @Test
    public void invalidAttemptTest() {
        ChallengeSolvedEvent challengeSolvedDTO = new ChallengeSolvedEvent(null, false, 3, 3, 1, "john");

        if(!(challengeSolvedDTO.isCorrect())) {
            System.out.println("false");
        }
    }
}
