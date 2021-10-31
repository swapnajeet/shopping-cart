package com.ssingh.shopping.api.error;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Getter
public class ErrorInfo {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;

    public ErrorInfo() {
        timeStamp = LocalDateTime.now();
    }

    public ErrorInfo(HttpStatus httpStatus, Throwable error) {
        this();
        this.httpStatus = httpStatus;
        this.message = error.getMessage();
    }
}
