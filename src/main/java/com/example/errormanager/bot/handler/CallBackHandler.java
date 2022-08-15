package com.example.errormanager.bot.handler;

import com.example.errormanager.api.criteria.ProjectCriteria;
import com.example.errormanager.api.dto.developer.DeveloperDTO;
import com.example.errormanager.api.dto.project.ProjectDTO;
import com.example.errormanager.api.enums.DeveloperRole;
import com.example.errormanager.api.service.DeveloperService;
import com.example.errormanager.api.service.ProjectService;
import com.example.errormanager.bot.ErrorManagerBot;
import com.example.errormanager.bot.enums.HomeMenuState;
import com.example.errormanager.bot.enums.UserServiceState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.example.errormanager.bot.states.State.*;

/**
 * @author Muhammadkomil Murodillayev, ср 11:16. 8/10/22
 */

@Component
public class CallBackHandler implements BaseHandler {

    private final DeveloperService developerService;

    private final ProjectService projectService;
    private final ErrorManagerBot bot;

    public CallBackHandler(DeveloperService developerService, ProjectService projectService, ErrorManagerBot bot) {
        this.developerService = developerService;
        this.projectService = projectService;
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
            List<ProjectDTO> projects = projectService.getAll(new ProjectCriteria());

            StringBuilder projectList = new StringBuilder();
            for (int i = 0; i < projects.size(); i++) {
                projectList.append(i + 1).append(". ").append(projects.get(i)).append("\n");
            }

            sendMessage.setText("<b>Projects\n<i>" + projectList + "</i>\n\nSet up\nexp: </b>set:project1,project2...");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("my_project")) {
            List<ProjectDTO> projects = projectService.getAll(developer.getId());

            StringBuilder projectList = new StringBuilder();
            for (int i = 0; i < projects.size(); i++) {
                projectList.append(i + 1).append(". ").append(projects.get(i)).append("\n");
            }
            sendMessage.setText("<b>My projects\n<i>" + projectList + "</i></b>");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("change_password"))) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("change_language"))) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("add_project")) {
            sendMessage.setText("<b>Input project name:<br><br>exp:</b> add_p:<i>name1,name2...</i>");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals("add_user")) {
            sendMessage.setText("<b>Add user:\n\nexp:</b> add_p:<i> full_name, username, password, role(TEAM_LEAD,PROGRAMMER)...</i>");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("all_user"))) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("delete_user"))) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().equals(("user_details"))) {
            sendMessage.setText("Tayyor emas");
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().startsWith(("add_user_true"))) {
            String username = callbackQuery.getData().substring(13);
            developerService.activated(username);
            bot.sendMessage(sendMessage);
        } else if (callbackQuery.getData().startsWith(("add_user_false"))) {
            String username = callbackQuery.getData().substring(14);
            developerService.deleteByUsername(username);
            bot.sendMessage(sendMessage);
        } else {
            DeleteMessage deleteMessage = new DeleteMessage();
            bot.sendDeleteMessage(deleteMessage);
        }

    }
}
