package com.example.crud_with_datajpa_thymeleaf.Exception;

/**
 * @author Admin
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
