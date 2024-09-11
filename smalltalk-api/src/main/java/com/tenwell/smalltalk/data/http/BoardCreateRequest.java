package com.tenwell.smalltalk.data.http;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class BoardCreateRequest {

    private String name;
    private String description;
    private Boolean enabled;

    protected BoardCreateRequest() {}

    protected BoardCreateRequest(String name, String description, Boolean enabled) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

}
