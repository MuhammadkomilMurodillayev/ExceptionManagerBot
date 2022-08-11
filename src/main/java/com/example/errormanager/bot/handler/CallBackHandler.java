package com.example.errormanager.bot.handler;

import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.bot.ErrorManagerBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Muhammadkomil Murodillayev, ср 11:16. 8/10/22
 */

@Component
public class CallBackHandler implements BaseHandler {

    private final DeveloperService developerService;

    private final ErrorManagerBot bot;

    public CallBackHandler(DeveloperService developerService, ErrorManagerBot bot) {
        this.developerService = developerService;
        this.bot = bot;
    }

    @Override
    public void handle(Update update) {

        CallbackQuery callbackQuery = update.getCallbackQuery();

        String chatId = callbackQuery.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        DeveloperDTO developer = developerService.getByChatId(chatId);

        if (callbackQuery.getData().equals("set_project")) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("my_project")) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("add_project") && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("all_project")) && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("project_details")) && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("add_user") && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("all_user")) && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("delete_user")) && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("user_details")) && developer.getRole().equals(DeveloperRole.TEAM_LEAD)) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else {
            DeleteMessage deleteMessage = new DeleteMessage();
            bot.sendDeleteMessage(deleteMessage);
        }

    }
}
