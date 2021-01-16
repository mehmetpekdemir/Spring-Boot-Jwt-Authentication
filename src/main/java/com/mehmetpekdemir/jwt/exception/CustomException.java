package com.mehmetpekdemir.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    @Getter
    private final HttpStatus httpStatus;

    @Override
    public String getMessage() {
        return message;
    }

}
