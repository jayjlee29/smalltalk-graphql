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

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import java.util.Collections;

@Slf4j
@Configuration
public class GraphqlTenwellConfig {

   

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Long redisPort;

    @Bean
    public ReactiveRedisMessageListenerContainer messageListener(ReactiveRedisConnectionFactory factory) {

        return new ReactiveRedisMessageListenerContainer(factory);
    }

    // @Bean
    // public GraphQLSchema graphQLSchema(TenwellController tenwellController) {
    //     return SchemaParser.newParser()
    //             .files("graphql/schema.graphqls")
    //             .resolvers(tenwellController)
    //             .options(SchemaParserOptions.newOptions().build())
    //             .build()
    //             .makeExecutableSchema();
    // }
    
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
