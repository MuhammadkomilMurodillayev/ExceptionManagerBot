package com.example.errormanager.bot.button;

import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.bot.enums.HomeMenuState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.example.errormanager.bot.states.State.getHomeMenuState;

/**
 * @author Muhammadkomil Murodillayev, ср 17:29. 8/10/22
 */
@Component
public class MarkupBoard {
    private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();
    private static final InlineKeyboardMarkup keyBoard = new InlineKeyboardMarkup();
    public void menu(SendMessage sendMessage, DeveloperRole role) {

        String chatId = sendMessage.getChatId();
        if (getHomeMenuState(chatId).equals(HomeMenuState.MAIN_MENU)) {

            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton("project service"));
            if (role.equals(DeveloperRole.TEAM_LEAD))
                row.add(new KeyboardButton("user service"));
            row.add(new KeyboardButton("settings"));
            board.setKeyboard(List.of(row));
            board.setResizeKeyboard(true);
            board.setSelective(true);
            sendMessage.setText("Tanlang");
            sendMessage.setReplyMarkup(board);
        } else if (getHomeMenuState(chatId).equals(HomeMenuState.PROJECT_SERVICE) && role.equals(DeveloperRole.PROGRAMMER)) {

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(new InlineKeyboardButton("set Project")));
            buttons.add(List.of(
                    new InlineKeyboardButton("my project")
            ));
            keyBoard.setKeyboard(buttons);
            sendMessage.setText("Tanlang");
            sendMessage.setReplyMarkup(keyBoard);

        } else if (getHomeMenuState(chatId).equals(HomeMenuState.PROJECT_SERVICE) && role.equals(DeveloperRole.TEAM_LEAD)) {

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(new InlineKeyboardButton("set Project")));
            buttons.add(List.of(
                    new InlineKeyboardButton("add project"),
                    new InlineKeyboardButton("my project"),
                    new InlineKeyboardButton("all project"),
                    new InlineKeyboardButton("project details")
            ));
            keyBoard.setKeyboard(buttons);
            sendMessage.setText("Tanlang");
            sendMessage.setReplyMarkup(keyBoard);

        } else if (getHomeMenuState(chatId).equals(HomeMenuState.USER_SERVICE) && role.equals(DeveloperRole.TEAM_LEAD)) {

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(new InlineKeyboardButton("add user")));
            buttons.add(List.of(
                    new InlineKeyboardButton("delete user"),
                    new InlineKeyboardButton("all user"),
                    new InlineKeyboardButton("user details")
            ));
            keyBoard.setKeyboard(buttons);
            sendMessage.setText("Tanlang");
            sendMessage.setReplyMarkup(keyBoard);

        } else if (getHomeMenuState(chatId).equals(HomeMenuState.SETTINGS)) {

            //TODO version 2

        }

    }

}
