package com.microservices.book.gamification.game;


import com.microservices.book.gamification.challenge.ChallengeSolvedDTO;
import com.microservices.book.gamification.game.domain.BadgeType;
import lombok.Value;

import java.util.List;

public interface GameService {

    GameResult newAttemptForUser(ChallengeSolvedDTO challenge);
    @Value
    class GameResult {
        int score;
        List<BadgeType> badges;
    }
}
