package com.microservices.book.gamification.game;

import com.microservices.book.gamification.game.LeaderBoardService;
import com.microservices.book.gamification.game.domain.LeaderBoardRow;
import com.microservices.book.gamification.repository.BadgeRepository;
import com.microservices.book.gamification.repository.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private final ScoreRepository scoreRepository;
    private final BadgeRepository badgeRepository;
    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
//        Get score
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();

        return scoreOnly.stream().map(row -> {
            List<String> badges = badgeRepository
                    .findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
                    .stream()
                    .map(b -> b.getBadgeType().getDescription())
                    .collect(Collectors.toList());
            return row.withBadges(badges);
        }).collect(Collectors.toList());
    }
}
