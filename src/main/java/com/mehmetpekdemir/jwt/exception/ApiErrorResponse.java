package com.mehmetpekdemir.jwt.exception;

import java.time.LocalDate;

import lombok.Getter;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Getter
public final class ApiErrorResponse {

    private int statusCode;

    private String message;

    private LocalDate date;

    public ApiErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        date = LocalDate.now();
    }

}