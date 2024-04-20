package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.quiz.QuestionDto;
import com.tinkoffedu.entity.question.Question;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = AnswerMapper.class)
public interface QuestionMapper {

    Question map(QuestionDto dto);

    QuestionDto map(Question question);

}
