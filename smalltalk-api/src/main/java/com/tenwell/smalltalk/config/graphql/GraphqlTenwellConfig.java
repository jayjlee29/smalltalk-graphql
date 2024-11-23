package com.tenwell.smalltalk.config.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.web.bind.annotation.RestController;

import com.tenwell.smalltalk.authorizer.SimpleSessionToken;
import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.controller.GraphqlArticleController;
import com.tenwell.smalltalk.controller.GraphqlBoardController;
import com.tenwell.smalltalk.controller.TenwellController;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class GraphqlTenwellConfig {

   

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Long redisPort;

    final private ApplicationContext applicationContext;


    @Bean
    public ReactiveRedisMessageListenerContainer messageListener(ReactiveRedisConnectionFactory factory) {

        return new ReactiveRedisMessageListenerContainer(factory);
    }
    
    @Bean
    public WebGraphQlInterceptor graphqlInterceptor() {
        return new WebGraphQlInterceptor() {
            @Override
            public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
                
                String token = request.getHeaders().getFirst(SimpleSessionToken.AUTHORIZATION_HEADER);
                TenwellSession session = SimpleSessionToken.buildToken(token);               

                request.configureExecutionInput((executionInput, builder) ->
                        builder.graphQLContext(Collections.singletonMap(SimpleSessionToken.SESSION_KEY , session))
                        .build());
                
                return chain.next(request).contextWrite(ctx->{
                    if(log.isDebugEnabled()) {
                        log.debug("context: {}", ctx);
                    }
                    return ctx.put(SimpleSessionToken.SESSION_KEY, session);
                });
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
