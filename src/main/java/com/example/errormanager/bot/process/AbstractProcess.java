package com.example.errormanager.bot.process;

import com.example.errormanager.api.service.BaseService;

public class AbstractProcess<S extends BaseService> {
    protected final S service;


    public AbstractProcess(S service) {

        this.service = service;
    }
}
