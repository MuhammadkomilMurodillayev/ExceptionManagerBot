package com.example.errormanager.bot.config;

import com.example.errormanager.bot.ErrorManagerBot;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author Muhammadkomil Murodillayev, ср 10:09. 8/10/22
 */

@Component
public class BotInitializer {
    final ErrorManagerBot bot;
    public BotInitializer(ErrorManagerBot bot) {
        this.bot = bot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {

        final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        telegramBotsApi.registerBot(bot);
    }
}
