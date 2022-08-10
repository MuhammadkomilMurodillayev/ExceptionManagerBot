package com.example.errormanager.bot.button;

import com.example.errormanager.bot.enums.HomeMenuState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static com.example.errormanager.bot.states.State.getHomeMenuState;

/**
 * @author Muhammadkomil Murodillayev, ср 17:29. 8/10/22
 */
@Component
public class MarkupBoard {
    private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();

    public static ReplyKeyboardMarkup teamLeadMenu(String chatId) {
        switch (getHomeMenuState(chatId).toString()) {
            case "HOME_MENU" -> {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("project service"));
                row.add(new KeyboardButton("user service"));
                row.add(new KeyboardButton("settings"));
                board.setKeyboard(List.of(row));
                board.setResizeKeyboard(true);
                board.setSelective(true);
                return board;
            }
            case "PROJECT_SERVICE" -> {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("add project"));
                row.add(new KeyboardButton("all projects"));
                row.add(new KeyboardButton("set projects"));
                row.add(new KeyboardButton("my projects"));

                KeyboardRow row1 = new KeyboardRow();
                row1.add(new KeyboardButton("delete project"));
                row1.add(new KeyboardButton("project details"));
                row1.add(new KeyboardButton("back"));
                board.setKeyboard(List.of(row, row1));
                board.setResizeKeyboard(true);
                board.setSelective(true);
                return board;
            }
            case "USER_SERVICE" -> {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("add user"));
                row.add(new KeyboardButton("all user"));

                KeyboardRow row1 = new KeyboardRow();
                row1.add(new KeyboardButton("delete user"));
                row1.add(new KeyboardButton("user details"));
                row1.add(new KeyboardButton("back"));
                board.setKeyboard(List.of(row, row1));
                board.setResizeKeyboard(true);
                board.setSelective(true);
                return board;
            }
            case "SETTINGS" -> {
                //TODO version 2
            }

        }
        return null;
    }

    public static ReplyKeyboardMarkup programmerMenu(String chatId) {

        switch (getHomeMenuState(chatId).toString()) {
            case "HOME_MENU" -> {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("project service"));
                row.add(new KeyboardButton("settings"));
                board.setKeyboard(List.of(row));
                board.setResizeKeyboard(true);
                board.setSelective(true);
                return board;
            }
            case "PROJECT_SERVICE" -> {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("set project"));
                row.add(new KeyboardButton("my project"));

                KeyboardRow row1 = new KeyboardRow();

                row1.add(new KeyboardButton("back"));

                board.setKeyboard(List.of(row, row1));
                board.setResizeKeyboard(true);
                board.setSelective(true);
                return board;
            }
            case "SETTINGS" -> {
                //TODO version 2
            }

        }
        return null;
    }

}
