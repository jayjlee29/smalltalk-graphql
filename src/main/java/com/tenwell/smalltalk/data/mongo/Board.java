package com.tenwell.smalltalk.data.mongo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Getter;


@Document
@Getter
@Builder
public class Board {

    @Id
    private String id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private boolean deleted;

    private boolean enabled;
    
    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    public boolean writable() {
        return !deleted && enabled;
    }

}
