package com.tinkoffedu.dto.mentee;

public record MentorMenteeRequest(
    Long mentorId,
    Long menteeId,
    Long mentorChatId,
    Long menteeChatId,
    String mentorName,
    String menteeName
) {

}
