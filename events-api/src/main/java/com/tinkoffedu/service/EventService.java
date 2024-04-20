package com.tinkoffedu.service;

import com.tinkoffedu.dto.event.EventRequest;
import com.tinkoffedu.dto.event.EventResponse;
import com.tinkoffedu.entity.Event;
import com.tinkoffedu.entity.Quiz;
import com.tinkoffedu.exception.InvalidArgumentException;
import com.tinkoffedu.exception.NotFoundException;
import com.tinkoffedu.mapper.EventMapper;
import com.tinkoffedu.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper mapper;

    @Transactional
    public EventResponse createEvent(EventRequest dto) {
        validateRequest(dto);

        var event = repository.save(mapper.map(dto));
        return mapper.map(event);
    }

    @Transactional(readOnly = true)
    public EventResponse getEvent(Long id) {
        var event = repository.findById(id).orElseThrow(
            () -> new NotFoundException(Event.class)
        );
        return mapper.map(event);
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest dto) {
        validateRequest(dto);

        var existingEvent = repository.findById(id).orElseThrow(
            () -> new NotFoundException(Event.class)
        );
        var event = mapper.map(dto).setQuiz(existingEvent.getQuiz());
        return mapper.map(repository.save(event));
    }

    @Transactional
    public void deleteEvent(Long id) {
        var existingEvent = repository.findById(id).orElseThrow(
            () -> new NotFoundException(Event.class)
        );
        repository.delete(existingEvent);
    }

    @Transactional
    public EventResponse addEventQuiz(Long id, Quiz quiz) {
        var existingEvent = repository.findById(id).orElseThrow(
            () -> new NotFoundException(Event.class)
        );
        return mapper.map(repository.save(existingEvent.setQuiz(quiz)));
    }

    // TODO: сделать нормальную валидацию
    private void validateRequest(EventRequest dto) {
        if (dto.name().length() > 64 || dto.description().length() > 256) {
            throw new InvalidArgumentException(
                "name should contain less than 64 chars and description should contain less than 256 chars"
            );
        }
        if (dto.closeDate().isBefore(dto.openDate())) {
            throw new InvalidArgumentException("event close date is early then open date");
        }
    }
}
