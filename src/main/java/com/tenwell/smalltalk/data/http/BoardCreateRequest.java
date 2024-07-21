package com.tenwell.smalltalk.data.http;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class BoardCreateRequest {

    private String name;
    private String description;
    private String createdBy;

    protected BoardCreateRequest() {}

    protected BoardCreateRequest(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

}
