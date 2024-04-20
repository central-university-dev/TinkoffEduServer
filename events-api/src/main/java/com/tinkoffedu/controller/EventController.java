package com.tinkoffedu.controller;

import com.tinkoffedu.dto.event.EventRequest;
import com.tinkoffedu.dto.event.EventResponse;
import com.tinkoffedu.dto.quiz.QuizRequest;
import com.tinkoffedu.dto.status.StatusResponse;
import com.tinkoffedu.endpoints.EventApi;
import com.tinkoffedu.service.EventService;
import com.tinkoffedu.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;
    private final QuizService quizService;

    @Override
    public EventResponse createEvent(EventRequest dto) {
        return eventService.createEvent(dto);
    }

    @Override
    public EventResponse getEvent(Long id) {
        return eventService.getEvent(id);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest dto) {
        return eventService.updateEvent(id, dto);
    }

    @Override
    public StatusResponse deleteEvent(Long id) {
        eventService.deleteEvent(id);
        return new StatusResponse("ok", null);
    }

    @Override
    public EventResponse addEventQuiz(Long id, QuizRequest dto) {
        var quiz = quizService.createQuiz(dto);
        return eventService.addEventQuiz(id, quiz);
    }

    @Override
    public EventResponse updateEventQuiz(Long id, QuizRequest dto) {
        quizService.updateQuiz(dto);
        return eventService.getEvent(id);
    }
}
