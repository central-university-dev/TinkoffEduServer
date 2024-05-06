package com.tinkoffedu.dto.indicators;

/**
 * Общие индикаторы для ментора и менти
 *
 * @param mood                настроение ментора/менти
 * @param weekBusyness        загруженность недели
 * @param weekDifficulty      сложность недели
 */
public record GeneralIndicators(
    Integer mood,
    Integer weekBusyness,
    Integer weekDifficulty
) {

}
