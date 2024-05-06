package com.tinkoffedu.dto.meeting;

import java.time.Instant;

public record MeetingResponse(
    Long id,
    String mentorName,
    String menteeName,
    Instant meetingDate
) {

}
