package com.tinkoffedu.dto.event;

import com.tinkoffedu.dto.quiz.QuizResponse;

import java.time.Instant;

public record EventResponse(
    Long id,
    String name,
    String description,
    Instant openDate,
    Instant closeDate,
    QuizResponse quiz
) {

}
