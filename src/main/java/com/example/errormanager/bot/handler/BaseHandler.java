package com.example.errormanager.bot.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Muhammadkomil Murodillayev, ср 10:40. 8/10/22
 */
public interface BaseHandler {

    void handle(Update update);
}
