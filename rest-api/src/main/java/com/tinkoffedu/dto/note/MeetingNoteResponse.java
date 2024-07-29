package com.tinkoffedu.dto.note;

public record MeetingNoteResponse(
    // Флаг для отображения полей на фронте
    Boolean isMentor,

    // Общие индикаторы
    Integer mood,
    Integer weekBusyness,
    Integer weekDifficulty,
    Integer communication,

    // Индикаторы ментора
    Integer problemDifficulty,
    Integer informationAwareness,
    Integer learningEffort,

    // Индикаторы менти
    Integer problemSolving,
    Integer clarity,

    // Общие сведения
    String message,
    String links
) {

}
