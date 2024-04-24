package com.tinkoffedu.service.bot;

import com.tinkoffedu.config.BotConfig;
import com.tinkoffedu.service.command.CommandsHandler;
import com.tinkoffedu.service.command.UnknownCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class MenteeBotService extends TelegramLongPollingBot {

    public final BotConfig botConfig;
    public final CommandsHandler handler;

    public MenteeBotService(BotConfig botConfig, CommandsHandler handler) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.handler = handler;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        var text = update.getMessage().getText();
        sendMessage(text.startsWith("/") ? handler.apply(update) : UnknownCommandService.getMessageOnUpdate(update));
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error while sending telegram message: {}", e.getMessage());
        }
    }

}
