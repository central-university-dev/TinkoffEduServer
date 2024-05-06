package com.tinkoffedu.dto.notes;

import com.tinkoffedu.dto.indicators.GeneralIndicators;

/**
 * Записи менти, которые получит ментор до встречи
 *
 * @param general    сведения об общем состоянии менти
 * @param notes      записи и ссылки
 */
public record PreMeeting(
    GeneralIndicators general,
    Notes notes
) {

}
