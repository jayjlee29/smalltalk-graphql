package com.tenwell.graphql.sample.data;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Comment {

    private String id;
    private String articleId;
    private String comment;
    private String author;    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
