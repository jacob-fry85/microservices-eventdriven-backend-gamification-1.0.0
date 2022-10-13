package com.microservices.book.gamification.game;

import com.microservices.book.gamification.game.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    /**
     * @reutrn the current leader board ranked from hight to low score
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
