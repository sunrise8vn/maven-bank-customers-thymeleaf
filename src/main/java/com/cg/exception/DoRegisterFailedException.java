package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoRegisterFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DoRegisterFailedException(String message) {
        super(message);
    }
}
