package com.example.errormanager.bot;

import com.example.errormanager.bot.config.BotConfig;
import com.example.errormanager.bot.handler.UpdateHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Muhammadkomil Murodillayev, ср 10:04. 8/10/22
 */

@Component
public class ErrorManagerBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final UpdateHandler updateHandler;

    public ErrorManagerBot(BotConfig config, @Lazy UpdateHandler updateHandler) {
        this.config = config;
        this.updateHandler = updateHandler;
    }

    @Override
    public String getBotUsername() {
        return config.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }
}
