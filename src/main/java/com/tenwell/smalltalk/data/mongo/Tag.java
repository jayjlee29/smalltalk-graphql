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

}
