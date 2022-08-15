package com.example.errormanager.api.exception.handler;

import com.example.errormanager.api.exception.BadCredentials;
import com.example.errormanager.api.exception.DeveloperNotFoundException;
import com.example.errormanager.api.exception.ForbiddenException;
import com.example.errormanager.bot.ErrorManagerBot;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * @author Muhammadkomil Murodillayev, чт 20:37. 8/11/22
 */

@ControllerAdvice(basePackages = "com.example.errormanager.api")
public class GlobalExceptionHandler {

    private final ErrorManagerBot bot;

    private static final SendMessage SEND_MESSAGE = new SendMessage();

    public GlobalExceptionHandler(ErrorManagerBot bot) {
        this.bot = bot;
    }

    @ExceptionHandler(value = {DeveloperNotFoundException.class})
    public void developerNotFoundExceptionHandler(DeveloperNotFoundException exception) {
        sendErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(value = {BadCredentials.class})
    public void badCredentialsHandler(BadCredentials exception) {
        sendErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public void badCredentialsHandler(ForbiddenException exception) {
        sendErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(value = {TelegramApiRequestException.class})
    public void badCredentialsHandler(TelegramApiRequestException exception) {
        System.out.println(exception.getMessage());
    }

    private void sendErrorMessage(String message) {
        SEND_MESSAGE.setText(message);
        bot.sendMessage(SEND_MESSAGE);
    }


}
