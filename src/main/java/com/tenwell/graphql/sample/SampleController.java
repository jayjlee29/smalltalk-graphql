package com.tenwell.graphql.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import graphql.ErrorType;
import graphql.GraphQLContext;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class SampleController {

    @Autowired
    private ReactiveRedisMessageListenerContainer messageListener;

    @Autowired
    private ReactiveRedisStreamListener reactiveRedisStreamListener;

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

    @SubscriptionMapping
    public Flux<String> subscribe(DataFetchingEnvironment env,
            @Argument("topic") String topic,
            GraphQLContext context) {

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
