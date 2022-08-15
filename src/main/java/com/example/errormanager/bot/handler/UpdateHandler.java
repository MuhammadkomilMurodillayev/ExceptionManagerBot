package com.example.errormanager.bot.handler;

import com.example.errormanager.bot.ErrorManagerBot;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Muhammadkomil Murodillayev, ср 10:39. 8/10/22
 */

@Service
public class UpdateHandler implements BaseHandler {

    private final MessageHandler messageHandler;
    private final CallBackHandler callBackHandler;

    private final ErrorManagerBot bot;

    public UpdateHandler(@Lazy MessageHandler messageHandler, @Lazy CallBackHandler callBackHandler, ErrorManagerBot bot) {
        this.messageHandler = messageHandler;
        this.callBackHandler = callBackHandler;
        this.bot = bot;
    }

    @Override
    public void handle(Update update) {

        if (update.hasMessage())
            messageHandler.handle(update);
        else if (update.hasCallbackQuery())
            callBackHandler.handle(update);
        else {
            try {
                bot.execute(
                        new DeleteMessage(update.getMessage().getChatId().toString(), update.getUpdateId())
                );
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
