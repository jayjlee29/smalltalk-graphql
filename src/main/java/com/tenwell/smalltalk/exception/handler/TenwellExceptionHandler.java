package com.tenwell.smalltalk.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tenwell.smalltalk.data.http.TenwellResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class TenwellExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Mono<TenwellResponse<Void>> exception(Exception e) {

        log.error("Exception occurred: ", e);
        return Mono.just(TenwellResponse.of(500, e.getMessage()));
    }
}