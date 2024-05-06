package com.tinkoffedu.dto.indicators;

/**
 * Индикаторы, получаемые от ментора после встречи с менти
 *
 * @param communication        насколько было хорошим общение с менти
 * @param problemDifficulty    насколько большие проблемы у менти
 * @param informationAwareness насколько менти осведомлён о процессе обучения
 * @param learningEffort       насколько менти заинтересован в обучении (он на гране отчисления?)
 */
public record MentorIndicators(
    Integer communication,
    Integer problemDifficulty,
    Integer informationAwareness,
    Integer learningEffort
) {

}
