package com.example.restservice.exception;

import com.example.restservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;

public class BadRequestException extends PlayServiceException {



    public BadRequestException(ErrorResponse response) {
        super(response);
    }

    public BadRequestException(ErrorResponse response, Throwable cause) {
        super(response, cause);
    }

    public static BadRequestException badRequest() {
        return new BadRequestException(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorConstants.DEFAULT_BAD_REQUEST_CODE,
                ErrorConstants.DEFAULT_BAD_REQUEST_MESSAGE, ""));
    }

    public static BadRequestException badRequest(String code, String message) {
        return new BadRequestException(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), code, message, ""));
    }

}
