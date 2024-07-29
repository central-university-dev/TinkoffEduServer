package com.tinkoffedu.dto.mentee;

public record MentorMenteeResponse(
    Long mentorId,
    Long menteeId,
    String mentorName,
    String menteeName
) {

}
