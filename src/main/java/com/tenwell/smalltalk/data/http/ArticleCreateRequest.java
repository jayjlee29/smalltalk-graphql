package com.tenwell.smalltalk.data.http;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ArticleCreateRequest {

    private String title;
    private String contents;
    private String author;
    private String[] tags;

    protected ArticleCreateRequest() {}

    protected ArticleCreateRequest(String title, String contents, String author, String[] tags) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.tags = tags;
    }

}
