package com.tenwell.graphql.sample.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tag {

    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

}
