package com.microservices.book.gamification.game.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * Enumeration with the different types of Badges that a user can win.
 */
@RequiredArgsConstructor
@Getter
public enum BadgeType {
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),

    FIRST_WON("First time"),
    LUCKY_NUMBER("Lucky number");

    private final String description;
}
