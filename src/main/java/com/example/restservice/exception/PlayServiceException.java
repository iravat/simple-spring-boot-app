package com.example.restservice.exception;

import com.example.restservice.model.ErrorResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayServiceException extends RuntimeException {

    private ErrorResponse response;
    public PlayServiceException(ErrorResponse response) {
        super(response.errorMessage());
        this.response = response;
    }

    public PlayServiceException(ErrorResponse response, Throwable cause) {
        super(response.errorMessage(), cause);
        this.response = response;
    }

    public ErrorResponse toResponse() {
        return this.response;
    }
}
