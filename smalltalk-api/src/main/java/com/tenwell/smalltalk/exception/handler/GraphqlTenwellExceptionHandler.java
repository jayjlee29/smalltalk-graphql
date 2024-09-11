package com.tenwell.smalltalk.exception.handler;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenwell.smalltalk.data.http.TenwellResponse;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GraphqlTenwellExceptionHandler {
    @GraphQlExceptionHandler
    public Mono<GraphQLError> handle(Exception ex) {
        log.error("Error GraphQL", ex);
        return Mono.just(GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.ExecutionAborted)
                .build());
    }
}