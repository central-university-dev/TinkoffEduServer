package com.tinkoffedu.dto.notes;

import com.tinkoffedu.dto.indicators.GeneralIndicators;
import com.tinkoffedu.dto.indicators.MentorIndicators;

/**
 * Записи ментора после встречи
 *
 * @param general    сведения об общем состоянии ментора
 * @param indicators сведения об успеваемости менти
 * @param notes      записи и ссылки
 */
public record MentorMeeting(
    GeneralIndicators general,
    MentorIndicators indicators,
    Notes notes
) {

}
