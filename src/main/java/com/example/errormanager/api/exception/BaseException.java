package com.example.errormanager.api.exception;

/**
 * @author Muhammadkomil Murodillayev, чт 20:43. 8/11/22
 */

public class BaseException extends RuntimeException{

    protected String message;
    public BaseException(String message) {
        this.message = message;
    }
}
