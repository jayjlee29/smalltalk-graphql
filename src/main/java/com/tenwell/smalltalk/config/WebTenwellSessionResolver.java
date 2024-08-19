package com.tenwell.smalltalk.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import com.tenwell.smalltalk.authorizer.SimpleSessionToken;
import com.tenwell.smalltalk.authorizer.TenwellSession;

import reactor.core.publisher.Mono;

public class WebTenwellSessionResolver implements HandlerMethodArgumentResolver {
   

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(TenwellSession.class);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        TenwellSession session = new SimpleSessionToken();
        session.parse(token);
        return Mono.just(session);
    }



}
