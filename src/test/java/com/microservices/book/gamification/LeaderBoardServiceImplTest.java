package com.microservices.book.gamification;

import com.microservices.book.gamification.game.LeaderBoardService;
import com.microservices.book.gamification.game.domain.LeaderBoardRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class LeaderBoardServiceImplTest {
    private LeaderBoardService leaderBoardService;

    @Test
    public void leaderBoardServiceTest() {
//        List<LeaderBoardRow> badges = leaderBoardService.getCurrentLeaderBoard();
//        System.out.println(badges.toString());
//        assertFalse(badges.isEmpty());
    }
}
