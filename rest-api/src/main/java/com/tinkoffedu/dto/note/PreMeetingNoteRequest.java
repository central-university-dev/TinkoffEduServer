package com.tinkoffedu.dto.note;

public record PreMeetingNoteRequest(
    // Общие индикаторы
    Integer mood,
    Integer weekBusyness,
    Integer weekDifficulty,

    // Общие сведения
    String message,
    String links
) {

}
