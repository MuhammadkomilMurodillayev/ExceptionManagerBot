package com.example.errormanager.bot.handler;

import com.example.errormanager.api.domain.ErrorMessage;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.api.service.ErrorService;
import com.example.errormanager.api.util.RootUtil;
import com.example.errormanager.bot.ErrorManagerBot;
import com.example.errormanager.bot.button.MarkupBoard;
import com.example.errormanager.bot.enums.DeveloperState;
import com.example.errormanager.bot.enums.HomeMenuState;
import com.example.errormanager.bot.process.DeveloperProcess;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import static com.example.errormanager.bot.states.State.*;

/**
 * @author Muhammadkomil Murodillayev, ср 11:14. 8/10/22
 */

@Component
public class MessageHandler implements BaseHandler {
    private final DeveloperProcess developerProcess;
    private final ErrorManagerBot bot;
    private final DeveloperService developerService;
    private final MarkupBoard markupBoard;
    private final RootUtil rootUtil;
    private final ErrorService errorService;

    public MessageHandler(DeveloperProcess developerProcess, ErrorManagerBot bot, DeveloperService developerService, MarkupBoard markupBoard, RootUtil rootUtil, ErrorService errorService) {
        this.developerProcess = developerProcess;

        this.bot = bot;
        this.developerService = developerService;
        this.markupBoard = markupBoard;
        this.rootUtil = rootUtil;
        this.errorService = errorService;
    }

    @Override
    public void handle(Update update) {

        try {
            if (update.getMessage().getText().equals("send"))
                throw new RuntimeException();

        } catch (RuntimeException e) {

            ErrorMessage errorMessage = new ErrorMessage();
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(rootUtil.getRoot());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            errorMessage.setStream(inputStream);
            errorMessage.setProjectId(1L);
            errorMessage.setProjectName("Uzagroin");
            errorMessage.setHappenTime(LocalDateTime.now());

            errorService.send(errorMessage);

        }

        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        String text = message.getText().toLowerCase();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (message.getText().equals("/start") && getDeveloperState(chatId).equals(DeveloperState.UNAUTHENTICATED)) {
            sendMessage.setText("<b>input username and password.\n\nexp:</b> <i>username/password</i>");
            bot.sendMessage(sendMessage);

        } else if (getDeveloperState(chatId).equals(DeveloperState.UNAUTHENTICATED) && text.contains("/")) {

            bot.sendMessage(developerProcess.login(sendMessage,message));

        } else if (getDeveloperState(chatId).equals(DeveloperState.AUTHENTICATED) && text.startsWith("set:")) {

            bot.sendMessage(developerProcess.setProject(sendMessage,message));

        } else if (getDeveloperState(chatId).equals(DeveloperState.AUTHENTICATED) && text.startsWith("add_u:")) {

            bot.sendMessage(developerProcess.addUser(sendMessage, text));

        } else if (getDeveloperState(chatId).equals(DeveloperState.AUTHENTICATED) && text.startsWith("logout")) {

            bot.sendMessage(developerProcess.logout(sendMessage,message));

        } else if (getDeveloperState(chatId).equals(DeveloperState.AUTHENTICATED)) {

            DeveloperDTO developer = developerService.getByChatId(chatId);

            if (message.getText().equalsIgnoreCase("project service"))
                setHomeMenuState(chatId, HomeMenuState.PROJECT_SERVICE);
            else if (message.getText().equalsIgnoreCase("settings"))
                setHomeMenuState(chatId, HomeMenuState.SETTINGS);
            else if (message.getText().equalsIgnoreCase("user service") && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
                setHomeMenuState(chatId, HomeMenuState.USER_SERVICE);

            } else {
                setHomeMenuState(chatId, HomeMenuState.MAIN_MENU);
                DeleteMessage deleteMessage = new DeleteMessage(chatId,message.getMessageId());
                bot.sendDeleteMessage(deleteMessage);
                return;
            }

            markupBoard.menu(sendMessage, developer);
            bot.sendMessage(sendMessage);
        } else {
            sendMessage.setText("Forbidden!");
            bot.sendMessage(sendMessage);
        }
    }
}
