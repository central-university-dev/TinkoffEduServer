package com.tinkoffedu.dto.quiz;

import java.util.List;

public record QuizResponseDto(
    Long id,
    String name,
    String description,
    List<QuestionDto> questions
) {

}
