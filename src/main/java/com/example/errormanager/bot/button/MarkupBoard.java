package com.example.errormanager.bot.button;

import com.example.errormanager.api.domain.Developer;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
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
    public static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();
    public static final InlineKeyboardMarkup keyBoard = new InlineKeyboardMarkup();

    public void menu(SendMessage sendMessage, DeveloperDTO developer) {
        DeveloperRole role = developer.getRole();

        String chatId = sendMessage.getChatId();
        if (getHomeMenuState(chatId).equals(HomeMenuState.MAIN_MENU)) {

            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton("project service"));
            if (role.equals(DeveloperRole.TEAM_LEAD))
                row.add(new KeyboardButton("user service"));
            row.add(new KeyboardButton("settings"));
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton("logout"));
            board.setKeyboard(List.of(row, row1));
            board.setResizeKeyboard(true);
            board.setSelective(true);
            sendMessage.setReplyMarkup(board);
        } else if (getHomeMenuState(chatId).equals(HomeMenuState.PROJECT_SERVICE)) {

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(InlineKeyboardButton.builder().text("set project").callbackData("set_project").build()));
            buttons.add(List.of(InlineKeyboardButton.builder().text("my project").callbackData("my_project").build()));
            if (role.equals(DeveloperRole.TEAM_LEAD))
                buttons.add(List.of(InlineKeyboardButton.builder().text("add project").callbackData("add_project").build()));

            keyBoard.setKeyboard(buttons);
            sendMessage.setText("=== Project service ===");
            sendMessage.setReplyMarkup(keyBoard);

        } else if (getHomeMenuState(chatId).equals(HomeMenuState.USER_SERVICE) && role.equals(DeveloperRole.TEAM_LEAD)) {

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(InlineKeyboardButton.builder().text("add user").callbackData("add_user").build()));
            buttons.add(List.of(
                    InlineKeyboardButton.builder().text("delete user").callbackData("delete_user").build(),
                    InlineKeyboardButton.builder().text("all user").callbackData("all_user").build(),
                    InlineKeyboardButton.builder().text("user details").callbackData("user_details").build()
            ));

            keyBoard.setKeyboard(buttons);
            sendMessage.setText("=== User sevice ===");
            sendMessage.setReplyMarkup(keyBoard);

        } else if (getHomeMenuState(chatId).equals(HomeMenuState.SETTINGS)) {
            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            buttons.add(List.of(InlineKeyboardButton.builder().text("change password").callbackData("change_password").build()));
            buttons.add(List.of(
                    InlineKeyboardButton.builder().text("change language").callbackData("change_password").build()
            ));

            keyBoard.setKeyboard(buttons);
            sendMessage.setText("<b>Settings\n" +
                    "\nName:</b> " + developer.getFullName() +
                    "\n<b>Username:</b> " + developer.getUsername()+
                    "\n<b>Role:</b> " + role +
                    "\n<b>Language:</b> " + developer.getLanguage());
            sendMessage.setReplyMarkup(keyBoard);
        }

    }

}
