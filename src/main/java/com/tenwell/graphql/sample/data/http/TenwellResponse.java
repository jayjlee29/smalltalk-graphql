package com.tenwell.graphql.sample.data.http;

import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
@Builder
public class TenwellResponse<T> {

    private int code;
    private T data;
    private String message;
    
    protected TenwellResponse() {}

    protected TenwellResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static <T> Mono<TenwellResponse<T>> ok(T data) {
        return Mono.just(new TenwellResponse<>(200, data, "OK"));
    }

    public static <T> TenwellResponse<T> of(int code, String message) {
        return new TenwellResponse<>(code, null, message);
    }

}
