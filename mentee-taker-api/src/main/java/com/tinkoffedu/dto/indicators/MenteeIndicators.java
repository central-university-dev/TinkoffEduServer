package com.tinkoffedu.dto.indicators;

/**
 * Индикаторы, получаемые от менти после встречи с ментором
 *
 * @param communication  насколько было хорошим общение с ментором
 * @param problemSolving были ли решены проблемы в ходе встречи
 * @param clarity        стали требования процессы ли понятнее после встречи
 */
public record MenteeIndicators(
    Integer communication,
    Integer problemSolving,
    Integer clarity
) {

}
