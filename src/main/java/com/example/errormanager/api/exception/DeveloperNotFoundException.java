package com.example.errormanager.api.exception;

public class DeveloperNotFoundException extends RuntimeException {

    public DeveloperNotFoundException(){
        super("developer not found");
    }
}
