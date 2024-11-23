package com.tenwell.smalltalk.exception.handler;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.language.SourceLocation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class GraphqlTenwellExceptionHandler {
    @GraphQlExceptionHandler
    public Mono<GraphQLError> handle(Exception ex) {
        log.error("Error GraphQL", ex);
        return Mono.just(GraphqlErrorBuilder.newError()
            .message(ex.getMessage())
            //.locations(Stream.of(ex.getStackTrace()).map(stack -> new SourceLocation(stack.getLineNumber(), 0, stack.getFileName())).toList())
            .extensions(Map.of("stacktraces", ex.toString()))
            .errorType(ErrorType.ExecutionAborted)
            .build());
    }
}