package com.microservices.book.gamification;

import com.microservices.book.gamification.challenge.ChallengeSolvedEvent;
import com.microservices.book.gamification.game.GameService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class GameEventHandlerTest {

    @Mock
    private GameService gameService;
    private ChallengeSolvedEvent challengeSolvedEvent;

    public ChallengeSolvedEvent getChallengeSolvedEvent() {
        return new ChallengeSolvedEvent(1L, true, 20, 30, 1L, "john");
    }

    @Test
    @RabbitListener(queues = "${amqp.queue.gamification}")
    void handleMultiplicationSolved() {
        challengeSolvedEvent = getChallengeSolvedEvent();

        log.info("Challenge Solved Event received: {}", challengeSolvedEvent.getAttemptId());;

        try {
            gameService.newAttemptForUser(challengeSolvedEvent);
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
//            Avoid the event to be requeued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
