package com.mehmetpekdemir.jwt.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomException(CustomException customException) {
        log.error(customException.getMessage(), customException);
        final ApiErrorResponse response = new ApiErrorResponse(400, customException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, WebRequest request) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        final ApiErrorResponse response = new ApiErrorResponse(400, methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> handleNullPointerException(NullPointerException nullPointerException) {
        log.error(nullPointerException.getMessage(), nullPointerException);
        final ApiErrorResponse response = new ApiErrorResponse(400, nullPointerException.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.error(accessDeniedException.getMessage(), accessDeniedException);
        final ApiErrorResponse response = new ApiErrorResponse(403, accessDeniedException.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        final ApiErrorResponse response = new ApiErrorResponse(500, exception.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}