package com.tinkoffedu.service.bot;

import com.tinkoffedu.config.BotConfig;
import com.tinkoffedu.service.command.CommandService;
import com.tinkoffedu.service.command.CommandsHandler;
import com.tinkoffedu.service.command.UnknownCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
public class MenteeBotService extends TelegramLongPollingBot {

    public final BotConfig botConfig;
    public final CommandsHandler handler;

    public MenteeBotService(BotConfig botConfig, CommandsHandler handler, List<CommandService> commandServices) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
        this.handler = handler;
        this.setMenteeBotCommands(commandServices);
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
        var text = update.getMessage().getText().replace(botConfig.getUsername(), "").trim();
        sendMessage(
            text.startsWith("/") ? handler.apply(update, text) : UnknownCommandService.getMessage(update)
        );
    }

    private void setMenteeBotCommands(List<CommandService> commandServices) {
        var commandsList = commandServices.stream()
            .filter(CommandService::shouldBeInMenu)
            .map(service -> new BotCommand(service.getCommand(), service.getDefinition()))
            .toList();
        var commands = SetMyCommands.builder()
            .languageCode("ru")
            .commands(commandsList)
            .build();
        try {
            execute(commands);
        } catch (TelegramApiException e) {
            log.error("Error while trying to set telegram bot commands list: {}", e.getMessage());
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error while sending telegram message: {}", e.getMessage());
        }
    }

}
