package com.tinkoffedu.dto.meeting;

import java.time.Instant;
import java.util.List;

public record MeetingsCreateRequest(Long mentorId, Long menteeId, List<Instant> meetingDates) {

}
