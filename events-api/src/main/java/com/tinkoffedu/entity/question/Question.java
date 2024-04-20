package com.tinkoffedu.entity.question;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record Question(String question, QuestionType type, List<Answer> answers) implements Serializable {

}
