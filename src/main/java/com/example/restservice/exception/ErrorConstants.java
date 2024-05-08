package com.example.restservice.exception;

public class ErrorConstants {
    public static final String DEFAULT_BAD_REQUEST_CODE = "PLAY-400";
    public static final String DEFAULT_BAD_REQUEST_MESSAGE = "Bad request parameters or payload. Please check your " +
            "request.";
    public static final String USER_NAME_EXISTS_CODE = "PLAY-1001";
    public static final String USER_NAME_EXISTS_MESSAGE = "Username is already taken!";
    public static final String ROLE_DOES_NOT_EXIST_CODE = "PLAY-1002";
    public static final String ROLE_DOES_NOT_EXIST_MESSAGE = "Role does not exist";
}
