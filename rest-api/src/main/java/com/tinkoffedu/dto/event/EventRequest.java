package com.tinkoffedu.dto.event;

import java.time.Instant;

public record EventRequest(
    Long id,
    String name,
    String description,
    Instant openDate,
    Instant closeDate
) {

}
