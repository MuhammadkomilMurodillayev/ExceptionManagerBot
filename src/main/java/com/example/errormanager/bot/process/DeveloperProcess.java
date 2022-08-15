package com.example.errormanager.bot.process;

import com.example.errormanager.api.dto.developer.DeveloperCreateDTO;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.developer.DeveloperUpdateDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.enums.Language;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.bot.button.MarkupBoard;
import com.example.errormanager.bot.enums.DeveloperState;
import com.example.errormanager.bot.enums.HomeMenuState;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.errormanager.bot.button.MarkupBoard.keyBoard;
import static com.example.errormanager.bot.states.State.setDeveloperState;
import static com.example.errormanager.bot.states.State.setHomeMenuState;

@Service
public class DeveloperProcess extends AbstractProcess<DeveloperService> {

    private final MarkupBoard markupBoard;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(8);

    public DeveloperProcess(DeveloperService service, MarkupBoard markupBoard) {
        super(service);
        this.markupBoard = markupBoard;
    }

    public SendMessage  addUser(SendMessage sendMessage, String text) {

        String[] userData = text.substring(6).trim().split(",");
        if (userData.length != 4) {
            sendMessage.setText("invalid data, please try again");
        } else {
            DeveloperCreateDTO newDeveloper = new DeveloperCreateDTO();
            newDeveloper.setFullName(userData[0]);
            newDeveloper.setUsername(userData[1].toLowerCase());
            newDeveloper.setPassword(ENCODER.encode(userData[2]));
            newDeveloper.setRole((userData[3].equalsIgnoreCase("TEAM_LEAD") ? DeveloperRole.TEAM_LEAD : DeveloperRole.PROGRAMMER));
            String sendData = "<b>Full name: </b>" +
                    userData[0] +
                    "<b>username: </b>" +
                    userData[1] +
                    "<b>password: </b>" +
                    userData[2] +
                    "<b>role: </b>" +
                    userData[3];
            sendMessage.setText(sendData);
            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

            buttons.add(List.of(
                    InlineKeyboardButton.builder().text("✅").callbackData("add_user_true" + userData[1]).build(),
                    InlineKeyboardButton.builder().text("❎").callbackData("add_user_false" + userData[1]).build()
            ));

            keyBoard.setKeyboard(buttons);
            sendMessage.setReplyMarkup(keyBoard);
        }

        return sendMessage;

    }

    public SendMessage setProject(SendMessage sendMessage, Message message) {

        String[] projects = message.getText().substring(4).trim().split(",");
        service.setProjects(projects, message.getChatId().toString());
        sendMessage.setText("Successfully added");

        return sendMessage;
    }

    public SendMessage login(SendMessage sendMessage, Message message) {
        String chatId = message.getChatId().toString();
        String[] usernameAndPassword = message.getText().split("/");
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];

        DeveloperDTO developer = service.getByUsername(username);

        if (ENCODER.matches(password, developer.getPassword()) && developer.getStatus() == 0) {

            setDeveloperState(chatId, DeveloperState.AUTHENTICATED);
            sendMessage.setText("Successfully authenticated");
            DeveloperUpdateDTO developerUpdateDTO = new DeveloperUpdateDTO();
            developerUpdateDTO.setChatId(chatId);
            developerUpdateDTO.setId(developer.getId());
            developerUpdateDTO.setLanguage(Language.valueOf(message.getFrom().getLanguageCode().toUpperCase()));
            service.update(developerUpdateDTO);
            markupBoard.menu(sendMessage, developer);

        } else sendMessage.setText("bad credentials");

        return sendMessage;
    }

    public SendMessage logout(SendMessage sendMessage, Message message) {

        String chatId = message.getChatId().toString();

        setDeveloperState(chatId, DeveloperState.UNAUTHENTICATED);
        setHomeMenuState(chatId, HomeMenuState.MAIN_MENU);
        sendMessage.setText("Bye");

        return sendMessage;
    }
}
