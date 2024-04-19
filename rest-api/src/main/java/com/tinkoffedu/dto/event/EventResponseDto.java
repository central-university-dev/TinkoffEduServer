package com.tinkoffedu.dto.event;

import com.tinkoffedu.dto.quiz.QuizResponseDto;

import java.time.Instant;

public record EventResponseDto(
    Long id,
    String name,
    String description,
    Instant openDate,
    Instant closeDate,
    QuizResponseDto quiz
) {

}
