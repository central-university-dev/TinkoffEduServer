package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.quiz.QuizRequest;
import com.tinkoffedu.dto.quiz.QuizResponse;
import com.tinkoffedu.entity.Quiz;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = QuestionMapper.class)
public interface QuizMapper {

    Quiz map(QuizRequest dto);

    QuizResponse map(Quiz quiz);

}
