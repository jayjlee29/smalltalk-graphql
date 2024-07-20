package com.tenwell.graphql.smalltalk.data.mongo;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Document
@Builder
@Getter
public class Comment {

    private String id;
    private String articleId;
    private String comment;
    private String author;    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updateArticleId(String articleId) {
        this.articleId = articleId;
    }
}
