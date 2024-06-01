package com.tenwell.graphql.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SampleExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception() {
        return ResponseEntity.internalServerError().build();
    }
}