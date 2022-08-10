package com.example.errormanager.bot.handler;

import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.bot.ErrorManagerBot;
import com.example.errormanager.bot.button.MarkupBoard;
import com.example.errormanager.bot.enums.DeveloperState;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.errormanager.bot.states.State.getDeveloperState;
import static com.example.errormanager.bot.states.State.setDeveloperState;

/**
 * @author Muhammadkomil Murodillayev, ср 11:14. 8/10/22
 */

@Component
public class MessageHandler implements BaseHandler {

    private final ErrorManagerBot bot;
    private final DeveloperService developerService;

    private final MarkupBoard markupBoard;


    public MessageHandler(ErrorManagerBot bot, DeveloperService developerService, MarkupBoard markupBoard) {
        this.bot = bot;
        this.developerService = developerService;
        this.markupBoard = markupBoard;
    }

    @Override
    public void handle(Update update) {

        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (message.getText().equals("/start") && getDeveloperState(chatId).equals(DeveloperState.UNAUTHENTICATED)) {
            sendMessage.setText("input username and password.\nexp: ☝️<i>username/password</i>");
            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (getDeveloperState(chatId).equals(DeveloperState.UNAUTHENTICATED)) {
            String[] usernameAndPassword = message.getText().split("/");

            DeveloperDTO developer = developerService.getByUsername(usernameAndPassword[0]);
            PasswordEncoder encoder = new BCryptPasswordEncoder(8);

            if (encoder.matches(usernameAndPassword[1], developer.getPassword())) {
                setDeveloperState(chatId, DeveloperState.AUTHENTICATED);
                sendMessage.setText("successfully authenticated");
                try {
                    bot.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
