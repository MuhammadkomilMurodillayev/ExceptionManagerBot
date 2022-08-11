package com.example.errormanager.api.exception;

/**
 * @author Muhammadkomil Murodillayev, чт 20:47. 8/11/22
 */
public class ForbiddenException extends BaseException {
    public ForbiddenException() {
        super("Forbidden!");
    }
}
