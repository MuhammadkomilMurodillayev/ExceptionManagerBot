package com.example.errormanager.api.controller;

import com.example.errormanager.api.domain.ErrorMessage;
import com.example.errormanager.api.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muhammadkomil Murodillayev, ср 10:23. 8/10/22
 */

@RestController
@RequestMapping("/error")
public class ErrorController {
    private final ErrorService service;
    @Autowired
    public ErrorController(ErrorService service) {
        this.service = service;
    }

    @PostMapping(value = "/send")
    public void send(@RequestBody ErrorMessage error) {
        service.send(error);
    }

}
