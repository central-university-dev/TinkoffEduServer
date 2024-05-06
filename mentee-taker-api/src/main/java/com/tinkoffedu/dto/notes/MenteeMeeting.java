package com.tinkoffedu.dto.notes;

import com.tinkoffedu.dto.indicators.GeneralIndicators;
import com.tinkoffedu.dto.indicators.MenteeIndicators;

/**
 * Записи менти после встречи
 *
 * @param general    сведения об общем состоянии менти
 * @param indicators сведения о решении проблем менти во время встречи
 * @param notes      записи и ссылки
 */
public record MenteeMeeting(
    GeneralIndicators general,
    MenteeIndicators indicators,
    Notes notes
) {

}
