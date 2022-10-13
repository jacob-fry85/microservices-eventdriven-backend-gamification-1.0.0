package com.microservices.book.gamification.game.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCard {
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cardId;

    private Long userId;
    private Long attemptId;

    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;

    private int score;

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
