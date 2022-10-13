package com.microservices.book.gamification.game;

import com.microservices.book.gamification.badgeProcessors.BadgeProcessor;
import com.microservices.book.gamification.game.domain.BadgeCard;
import com.microservices.book.gamification.game.domain.BadgeType;
import com.microservices.book.gamification.game.domain.ScoreCard;
import com.microservices.book.gamification.repository.BadgeRepository;
import com.microservices.book.gamification.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.microservices.book.gamification.challenge.ChallengeSolvedDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;
    private final List<BadgeProcessor> badgeProcessors;

    @Override
    public GameResult newAttemptForUser(final ChallengeSolvedDTO challenge) {
        if(challenge.isCorrect()) {
            ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());
            scoreRepository.save(scoreCard);
            log.info("User {} scored {} points for attempt id {}", challenge.getUserAlias(), scoreCard.getScore(), challenge.getAttemptId());
            List<BadgeCard> badgeCards = processForBadges(challenge);
            return new GameResult(scoreCard.getScore(),
                    badgeCards.stream().map(BadgeCard::getBadgeType).collect(Collectors.toList()));
        } else {
            log.info("Attempt id {} is not correct. " + "User {} does not get score.",
                    challenge.getAttemptId(), challenge.getUserAlias());
            return new GameResult(0, List.of());
        }
    }

    /**
     * Checks the total score and the different score cards obtained
     * to give new badges in case their conditions are met
     */
    private List<BadgeCard> processForBadges(final ChallengeSolvedDTO solveChallenge) {
        Optional<Integer> optTotalScore = scoreRepository.getTotalScoreforUser(solveChallenge.getUserId());
        log.info("OptTotalScore : " + optTotalScore.toString());

        if(optTotalScore.isEmpty()) return Collections.emptyList();
        int totalScore = optTotalScore.get();

//        Get the total score and existing badges for that user
        List<ScoreCard> scorecardList = scoreRepository
                .findByUserIdOrderByScoreTimestampDesc(solveChallenge.getUserId());

        Set<BadgeType> alreadyGotBadges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(solveChallenge.getUserId())
                .stream()
                .map(BadgeCard::getBadgeType)
                .collect(Collectors.toSet());

        log.info("alreadyGotBadges : {}", alreadyGotBadges.toString());


//        Calls the badge processors for badges that the user doesnt have yet
        List<BadgeCard> newBadgeCards = badgeProcessors.stream()
                                        .filter(bp -> !alreadyGotBadges.contains(bp.badgeType()))
                                        .map(bp -> bp.processForOptionalBadge(totalScore, scorecardList, solveChallenge))
                                        .flatMap(Optional::stream)// returns an empty stream if empty
                                        // maps the optionals if present to new BadgeCards
                                        .map(badgeType -> new BadgeCard(solveChallenge.getUserId(), badgeType))
                                        .collect(Collectors.toList());
        log.info("New BadgeCards : {}", newBadgeCards.toString());

        badgeRepository.saveAll(newBadgeCards);

        return newBadgeCards;
    }
}
