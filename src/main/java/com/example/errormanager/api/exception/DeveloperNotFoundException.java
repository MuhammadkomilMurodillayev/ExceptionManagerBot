package com.example.errormanager.api.exception;

public class DeveloperNotFoundException extends BaseException {

    public DeveloperNotFoundException(){
        super("developer not found");
    }
}
