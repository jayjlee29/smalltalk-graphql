package com.tenwell.smalltalk.config;

import java.util.List;
import java.util.Map;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.graphql.server.WebSocketGraphQlInterceptor;
import org.springframework.graphql.server.WebSocketSessionInfo;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
class SubscriptionInterceptor implements WebSocketGraphQlInterceptor {

    @Override
    public Mono<Object> handleConnectionInitialization(WebSocketSessionInfo sessionInfo,
            Map<String, Object> connectionInitPayload) {
        log.info("Connection init payload: {}", connectionInitPayload);
        // sessionInfo.getHeaders().add("token",
        // connectionInitPayload.get("token").toString());
        return Mono.empty();
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        log.info("Intercepting request: {}", request);
        List<String> token = request.getHeaders().getOrEmpty("token");
        return chain.next(request);
    }
}