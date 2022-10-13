package com.microservices.book.gamification.badgeProcessors;

import com.microservices.book.gamification.challenge.ChallengeSolvedDTO;
import com.microservices.book.gamification.game.domain.BadgeType;
import com.microservices.book.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LuckyNumberBadgeProcessor implements BadgeProcessor{
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {
        return (solved.getFactorA() == 42 || solved.getFactorB() == 42) ? Optional.of((BadgeType.LUCKY_NUMBER)) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCKY_NUMBER;
    }
}
