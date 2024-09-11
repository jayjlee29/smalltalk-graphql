package com.tenwell.smalltalk.exception;

public class ArticleNotFoundException extends TenwellException {

    private String articleId;

    public ArticleNotFoundException(String articleId) {
        super("Article not found: " + articleId);
        this.articleId = articleId;
    }

    public ArticleNotFoundException(String message, String articleId, Throwable cause) {
        super(message, cause);
        this.articleId = articleId;
    }


}
