package com.ssingh.shopping.api.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> createErrorResponseForException(Exception exception) {
        return createErrorResponse(new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, exception));
    }

    private ResponseEntity<Object> createErrorResponse(ErrorInfo errorInfo) {
        return new ResponseEntity<>(errorInfo, errorInfo.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return createErrorResponse(new ErrorInfo(HttpStatus.BAD_REQUEST, new RuntimeException(errors.toString())));
    }
}