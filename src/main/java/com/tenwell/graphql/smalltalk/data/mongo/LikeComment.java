package com.tenwell.graphql.smalltalk.data.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import nonapi.io.github.classgraph.json.Id;

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

}
