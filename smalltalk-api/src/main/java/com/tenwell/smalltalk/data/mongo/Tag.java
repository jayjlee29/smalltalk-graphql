package com.tenwell.smalltalk.data.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Document
@Getter
@Builder
public class Tag {

    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

    protected Tag() {
    }

    protected Tag(String id, String name, String description, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

}
