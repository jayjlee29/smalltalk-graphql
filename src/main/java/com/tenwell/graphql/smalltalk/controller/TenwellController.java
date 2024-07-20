package com.tenwell.graphql.smalltalk.controller;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.tenwell.graphql.smalltalk.config.ReactiveRedisStreamListener;

import graphql.ErrorType;
import graphql.GraphQLContext;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TenwellController {

    
    final private ReactiveRedisMessageListenerContainer messageListener;

    
    final private ReactiveRedisStreamListener reactiveRedisStreamListener;

    final private RedisOperations<String, String> redisOperations;

    @QueryMapping
    public String greeting() {
        return "Hello, GraphQL!";
    }

    @QueryMapping
    public Mono<String> greetingMono() {
        return Mono.just("Hello, GraphQL!");
    }

    @QueryMapping
    public Flux<String> greetingsFlux() {
        return Flux.just("Hello", "GraphQL", "World");
    }

    @MutationMapping
    public Mono<Long> publish(DataFetchingEnvironment env,
            @Argument("topic") String topic,
            @Argument("message") String message) {

        log.info("Publishing message: {} to topic: {}", message, topic);
        Long numberOfClients = redisOperations.convertAndSend(topic, message);

        return Mono.just(numberOfClients);
    }

    @SubscriptionMapping
    public Flux<String> subscribe(DataFetchingEnvironment env, @Argument("topic") String topic, GraphQLContext context) {

        // String topic = env.getArgument("topic");
        log.info("Subscribing to topic: {}", topic);
        return messageListener.receive(ChannelTopic.of(topic)).map(message -> {
            log.info("Received message: {}", message);
            return message.getMessage();
        });
    }

    @SubscriptionMapping
    public Flux<String> stream() {
        return reactiveRedisStreamListener.receiveMessagesFromChannel("stream:channel").map(message -> {
            log.info("Received message: {}", message);
            return message.getValue().get("message");
        });

    }

    @GraphQlExceptionHandler
    public GraphQLError handle(Exception ex) {
        log.error("Error", ex);
        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }

}
