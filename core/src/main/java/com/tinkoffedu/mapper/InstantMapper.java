package com.tinkoffedu.mapper;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface InstantMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC));

    default String map(Instant instant) {
        return formatter.format(instant);
    }
}
