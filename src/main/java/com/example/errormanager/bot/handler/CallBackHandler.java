package com.example.errormanager.bot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Muhammadkomil Murodillayev, ср 11:16. 8/10/22
 */

@Component
public class CallBackHandler implements BaseHandler{
    @Override
    public void handle(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

    }
}
