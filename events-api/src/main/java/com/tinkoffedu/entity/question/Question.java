package com.tinkoffedu.entity.question;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Question implements Serializable {

    String question;
    QuestionType type;
    List<Answer> answers;
}
