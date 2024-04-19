package com.tinkoffedu.service;

import com.tinkoffedu.dto.event.EventRequestDto;
import com.tinkoffedu.entity.Event;
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
    public void createEvent(EventRequestDto dto) {
        validateRequest(dto);

        // repository.save(mapper.map(dto));
    }

    @Transactional
    public void updateEvent(Long id, EventRequestDto dto) {
        validateRequest(dto);

        var existingEvent = repository.findById(id).orElseThrow(
            () -> new NotFoundException(Event.class)
        );
        // var event = mapper.map(dto).setId(id);
        // repository.save(event);
    }

    // TODO: сделать нормальную валидацию
    private void validateRequest(EventRequestDto dto) {
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
