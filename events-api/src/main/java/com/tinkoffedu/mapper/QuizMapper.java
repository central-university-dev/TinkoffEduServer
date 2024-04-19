package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.event.EventRequestDto;
import com.tinkoffedu.dto.event.EventResponseDto;
import com.tinkoffedu.dto.quiz.QuizRequestDto;
import com.tinkoffedu.dto.quiz.QuizResponseDto;
import com.tinkoffedu.entity.Event;
import com.tinkoffedu.entity.Quiz;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = InstantMapper.class)
public interface QuizMapper {

    // Quiz map(QuizRequestDto dto);

    // QuizResponseDto map(Quiz quiz);

}
