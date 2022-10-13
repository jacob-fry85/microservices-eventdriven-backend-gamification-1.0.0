package com.microservices.book.gamification.repository;

import com.microservices.book.gamification.game.domain.BadgeType;
import com.microservices.book.gamification.game.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

    Set<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
