package com.tenwell.graphql.sample.exception;

public class ArticlePublishException extends RuntimeException {

    public ArticlePublishException(String message) {
        super(message);
    }

    public ArticlePublishException(String message, Throwable cause) {
        super(message, cause);
    }

}
