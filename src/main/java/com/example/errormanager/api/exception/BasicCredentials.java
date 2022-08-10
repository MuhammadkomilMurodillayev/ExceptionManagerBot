package com.example.errormanager.api.exception;

/**
 * @author Muhammadkomil Murodillayev, ср 11:52. 8/10/22
 */
public class BasicCredentials extends RuntimeException {

    public BasicCredentials() {
        super("bad credentials");
    }
}
