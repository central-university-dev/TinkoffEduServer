package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.quiz.AnswerDto;
import com.tinkoffedu.entity.question.Answer;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface AnswerMapper {

    Answer map(AnswerDto dto);

    AnswerDto map(Answer answer);

}
