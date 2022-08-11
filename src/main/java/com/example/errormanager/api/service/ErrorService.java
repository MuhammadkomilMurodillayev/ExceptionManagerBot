package com.example.errormanager.api.service;

import com.example.errormanager.api.domain.ErrorMessage;
import com.example.errormanager.api.dto.project.SendErrorDTO;
import com.example.errormanager.bot.ErrorManagerBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author Muhammadkomil Murodillayev, ср 10:58. 8/10/22
 */

@Service
public class ErrorService {

    private final ErrorManagerBot bot;

    private final DeveloperService developerService;

    public ErrorService(ErrorManagerBot bot, DeveloperService developerService) {
        this.bot = bot;
        this.developerService = developerService;
    }

    public void send(ErrorMessage error) {

        List<SendErrorDTO> sendErrorDTOS = developerService.getDeveloperChatId(error.getProjectId());
        SendDocument sendDocument = new SendDocument();

        InputFile inputFile = new InputFile(error.getStream(), "error");

        sendErrorDTOS.forEach((s) -> {

            sendDocument.setCaption("<b>Project name:</b>" + s.getName() + "<br><b>Time</b>:" + error.getHappenTime());
            sendDocument.setDocument(inputFile);
            bot.sendDocument(sendDocument);
        });
    }

    public void sendError(ErrorMessage error) {

        List<SendErrorDTO> sendErrorDTOS = developerService.getDeveloperChatId(error.getProjectId());

        File file;
        String prefix = "error";
        String suffix = ".txt";

        File directoryPath = new File("/src/main/resources/sendFile");


        try {
            file = File.createTempFile(prefix, suffix, directoryPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SendDocument sendDocument = new SendDocument();
        InputFile inputFile = new InputFile(file);

        sendErrorDTOS.forEach((s) -> {

            sendDocument.setCaption("<b>Project name:</b>" + s.getName() + "<br><b>Time</b>:" + error.getHappenTime());
            sendDocument.setDocument(inputFile);
            sendDocument.setChatId(s.getChatId());
            bot.sendDocument(sendDocument);

        });
    }
}
