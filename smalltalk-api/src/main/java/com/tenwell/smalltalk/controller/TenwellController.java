package com.tenwell.smalltalk.controller;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.config.graphql.GraphqlTenwellStreamListener;
import com.tenwell.smalltalk.data.http.ArticleCreateRequest;
import com.tenwell.smalltalk.data.http.BoardCreateRequest;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Article;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.service.ArticleService;
import com.tenwell.smalltalk.service.BoardService;

import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Controller
@Validated
public class TenwellController {
    
    final private ReactiveRedisMessageListenerContainer messageListener;

    final private GraphqlTenwellStreamListener graphqlMessageListener;

    final private ReactiveRedisOperations<String, String> redisOperations;

    final private ArticleService articleService;

    final private BoardService boardService;

    @QueryMapping
    public String greeting() {
        return "Hello, GraphQL!";
    }

    @QueryMapping
    public Mono<String> greetingMono() {
        return Mono.just("Hello, GraphQL!");
    }

    @QueryMapping
    public Flux<String> greetingsFlux(@ContextValue(name="session") TenwellSession session) {
        //TenwellSession session = context.get("session");
        return Flux.just(session.getUserName(), session.getUserId());
    }

    @MutationMapping
    public Mono<Long> publish(DataFetchingEnvironment env,
            @Argument("topic") String topic,
            @Argument("message") String message) {

        log.info("Publishing message: {} to topic: {}", message, topic);
        return redisOperations.convertAndSend(topic, message);
    }



    @MutationMapping
    public Mono<TenwellResponse<Board>> createBoard(
        @ContextValue(name="session") TenwellSession session,
        @Valid @Argument(name="input") BoardCreateRequest request) {

        log.info("Creating board: {} {}", session, request);
        return boardService.createBoard(session, request)
        .doOnSuccess(board -> log.info("Board created: {}", board))
        .flatMap(board-> TenwellResponse.ok(board));
        
    }

    /**
     * redis pub/sub을 이용한 subscription
     * @param env
     * @param topic
     * @param context
     * @return
     */
    @SubscriptionMapping
    public Flux<String> subscribe(DataFetchingEnvironment env, @Argument("topic") String topic, GraphQLContext context) {

        // String topic = env.getArgument("topic");
        log.info("Subscribing to topic: {}", topic);
        return messageListener.receive(ChannelTopic.of(topic)).map(message -> {
            log.info("Received message: {}", message);
            return message.getMessage();
        });
    }

    /**
     * redis stream을 이용한 subscription
     * @return
     */
    @SubscriptionMapping
    public Flux<String> stream() {
        return graphqlMessageListener.receiveMessagesFromChannel("stream:channel").map(message -> {
            log.info("Received message: {}", message);
            return message.getValue().get("message");
        });

    }

    // @GraphQlExceptionHandler
    // public GraphQLError handle(Exception ex) {
    //     log.error("Error GraphQL", ex);
    //     return GraphqlErrorBuilder.newError()
    //             .message(ex.getMessage())
    //             .errorType(ErrorType.DataFetchingException)
    //             .build();
    // }

}
