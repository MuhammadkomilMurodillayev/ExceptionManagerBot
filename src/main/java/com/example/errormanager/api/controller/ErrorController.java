package com.example.errormanager.api.controller;

import com.example.errormanager.api.domain.ErrorMessage;
import com.example.errormanager.api.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/send")
    public void send(@RequestBody ErrorMessage error) {
        service.send(error);
    }

}
