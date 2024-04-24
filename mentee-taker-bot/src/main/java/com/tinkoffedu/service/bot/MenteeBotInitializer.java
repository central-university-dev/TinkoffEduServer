package com.tinkoffedu.service.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenteeBotInitializer {

    private final MenteeBotService menteeBot;

    @EventListener(ContextRefreshedEvent.class)
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            log.info("MenteeBot initialization started");
            telegramBotsApi.registerBot(menteeBot);
            log.info("MenteeBot successfully initialized");
        } catch (TelegramApiException e) {
            log.error("Error occurred while registering bot: " + e.getMessage());
        }
    }

}
