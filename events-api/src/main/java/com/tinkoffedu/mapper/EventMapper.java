package com.tinkoffedu.mapper;

import com.tinkoffedu.config.MapStructConfig;
import com.tinkoffedu.dto.event.EventRequestDto;
import com.tinkoffedu.dto.event.EventResponseDto;
import com.tinkoffedu.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, componentModel = "spring", uses = InstantMapper.class)
public interface EventMapper {

    @Mapping(target = "imageFilename", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    Event map(EventRequestDto dto);

     EventResponseDto map(Event event);

}
