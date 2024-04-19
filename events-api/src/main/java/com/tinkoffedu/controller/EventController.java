package com.tinkoffedu.controller;

import com.tinkoffedu.dto.event.EventRequestDto;
import com.tinkoffedu.dto.event.EventResponseDto;
import com.tinkoffedu.dto.quiz.QuizRequestDto;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.endpoints.EventApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {


    @Override
    public StatusResponse createEvent(EventRequestDto dto) {
        return null;
    }

    @Override
    public EventResponseDto getEvent(Long id) {
        return null;
    }

    @Override
    public StatusResponse updateEvent(Long id, EventRequestDto dto) {
        return null;
    }

    @Override
    public StatusResponse deleteEvent(Long id) {
        return null;
    }

    @Override
    public StatusResponse addEventQuiz(Long id, QuizRequestDto dto) {
        return null;
    }

    @Override
    public StatusResponse updateEventQuiz(Long id, QuizRequestDto dto) {
        return null;
    }
}
