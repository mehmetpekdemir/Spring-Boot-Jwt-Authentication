package com.mehmetpekdemir.jwt.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Getter
public final class ApiErrorResponse {

    private int statusCode;

    private String message;

    private final String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

    public ApiErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}