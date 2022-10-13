package com.microservices.book.gamification;

import com.microservices.book.gamification.challenge.ChallengeSolvedDTO;
import com.microservices.book.gamification.game.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLOutput;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    private GameService gameService;

    @Test
    public void correctAttemptTest() {
        ChallengeSolvedDTO challengeSolvedDTO = new ChallengeSolvedDTO(null, true, 2, 2, 1, "john-doe");

        if(challengeSolvedDTO.isCorrect()) {
            System.out.println("correct");
        }
    }

    @Test
    public void invalidAttemptTest() {
        ChallengeSolvedDTO challengeSolvedDTO = new ChallengeSolvedDTO(null, false, 3, 3, 1, "john-doe");

        if(!(challengeSolvedDTO.isCorrect())) {
            System.out.println("false");
        }
    }
}
