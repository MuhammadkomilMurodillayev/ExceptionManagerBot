package com.example.errormanager.bot.handler;

import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.bot.ErrorManagerBot;
import com.example.errormanager.bot.button.MarkupBoard;
import com.example.errormanager.bot.enums.DeveloperState;
import com.example.errormanager.bot.enums.HomeMenuState;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.errormanager.bot.states.State.*;

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
            bot.sendMessage(sendMessage);

        } else if (getDeveloperState(chatId).equals(DeveloperState.UNAUTHENTICATED)) {

            String[] usernameAndPassword = message.getText().split("/");
            String username = usernameAndPassword[0];
            String password = usernameAndPassword[1];

            DeveloperDTO developer = developerService.getByUsername(username);
            PasswordEncoder encoder = new BCryptPasswordEncoder(8);

            if (encoder.matches(password, developer.getPassword())) {

                setDeveloperState(chatId, DeveloperState.AUTHENTICATED);

                sendMessage.setText("successfully authenticated");
                DeveloperUpdateDTO developerUpdateDTO = new DeveloperUpdateDTO();
                developerUpdateDTO.setChatId(chatId);
                developerUpdateDTO.setId(developer.getId());
                developerService.update(developerUpdateDTO);
                markupBoard.menu(sendMessage, developer.getRole());
                bot.sendMessage(sendMessage);
            }
        } else if (getDeveloperState(chatId).equals(DeveloperState.AUTHENTICATED)) {

            DeveloperDTO developer = developerService.getByChatId(chatId);

            if (message.getText().equalsIgnoreCase("project service"))
                setHomeMenuState(chatId, HomeMenuState.PROJECT_SERVICE);
            else if (message.getText().equalsIgnoreCase("settings"))
                setHomeMenuState(chatId, HomeMenuState.SETTINGS);
            else if (message.getText().equalsIgnoreCase("user service") && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
                setHomeMenuState(chatId, HomeMenuState.USER_SERVICE);

            } else setHomeMenuState(chatId, HomeMenuState.MAIN_MENU);

            markupBoard.menu(sendMessage, developer.getRole());
            bot.sendMessage(sendMessage);
        }

    }
}
