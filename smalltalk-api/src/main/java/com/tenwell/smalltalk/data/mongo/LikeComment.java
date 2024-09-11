package com.tenwell.smalltalk.data.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;


@Document
@Getter
@Builder
public class LikeComment {

    @Id
    private String id;
    private String articleId;
    private String userId;
    private String createdAt;
    private String updatedAt;
    protected LikeComment() {
    }
    protected LikeComment(String id, String articleId, String userId, String createdAt, String updatedAt) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

}
