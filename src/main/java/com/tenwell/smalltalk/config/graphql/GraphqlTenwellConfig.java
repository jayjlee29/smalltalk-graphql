package com.tenwell.smalltalk.config.graphql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;

import com.tenwell.smalltalk.authorizer.SimpleSessionToken;
import com.tenwell.smalltalk.authorizer.TenwellSession;

import reactor.core.publisher.Mono;
import java.util.Collections;

@Configuration
public class GraphqlConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Long redisPort;

    @Bean
    public ReactiveRedisMessageListenerContainer messageListener(ReactiveRedisConnectionFactory factory) {

        return new ReactiveRedisMessageListenerContainer(factory);
    }

    
    @Bean
    public WebGraphQlInterceptor graphqlInterceptor() {
        return new WebGraphQlInterceptor() {
            @Override
            public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
                
                String token = request.getHeaders().getFirst("Authorization");

                TenwellSession session = new SimpleSessionToken();
                session.parse(token);
                
                request.configureExecutionInput((executionInput, builder) ->
                        builder.graphQLContext(Collections.singletonMap("session", session))
                        .build());
                return chain.next(request);
            }
        };
    }

    // @Bean
    // public GraphQlHttpHandler graphQlHttpHandler(GraphQlService graphQlService, WebGraphQlInterceptor graphqlInterceptor) {
    //     return GraphQlHttpHandler.builder(graphQlService)
    //             .interceptor(graphqlInterceptor)
    //             .build();
    // }

    // @Bean
    // public GraphQlWebSocketHandler graphQlWebSocketHandler(GraphQlService graphQlService, WebGraphQlInterceptor graphqlInterceptor) {
    //     return GraphQlWebSocketHandler.builder(graphQlService)
    //             .interceptor(graphqlInterceptor)
    //             .build();
    // }

}
