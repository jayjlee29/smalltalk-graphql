package com.tenwell.smalltalk.client;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class ArticleLikePublisher {
    
    final private ReactiveRedisOperations<String, String> reactiveRedisOperations;

    public Mono<Void> publish(String articleId, String userId) {
        log.info("Publishing for article: {} by user: {}", articleId, userId);
        String topic = String.format("article/%s/like", articleId);
        return reactiveRedisOperations.convertAndSend(topic, userId).then();
    }


}
