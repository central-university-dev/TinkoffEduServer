package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.event.EventRequest;
import com.tinkoffedu.dto.event.EventResponse;
import com.tinkoffedu.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = {InstantMapper.class, QuizMapper.class})
public interface EventMapper {

    @Mapping(target = "imageFilename", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    Event map(EventRequest dto);

    EventResponse map(Event event);

}
