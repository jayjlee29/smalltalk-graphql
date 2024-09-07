package com.tenwell.smalltalk.data.mongo;

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

    protected Comment() {
    }
   
    protected Comment(String id, String articleId, String comment, String author, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.articleId = articleId;
        this.comment = comment;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public void updateArticleId(String articleId) {
        this.articleId = articleId;
    }
}
