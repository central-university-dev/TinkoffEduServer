package com.tinkoffedu.dto.quiz;

import java.util.List;

public record QuizRequestDto(
    Long id,
    String name,
    String description,
    List<QuestionDto> questions
) {

}
