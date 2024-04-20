package com.tinkoffedu.dto.quiz;

import java.util.List;

public record QuestionDto(String question, String type, List<AnswerDto> answers) {

}
