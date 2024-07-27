package com.tenwell.smalltalk.data.mongo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Document(collection = "board")
@Getter
@Builder
public class Board {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("deleted")
    private boolean deleted;

    @Field("enabled")
    private boolean enabled;
    
    
    @Field("createdAt") @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();;

    @Field("createdBy")
    private String createdBy;

    @Field("updatedAt") @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Field("updatedBy")
    private String updatedBy;

    @Field("checkBoardAvailable")
    public boolean checkBoardAvailable() {
        return !deleted && enabled;
    }


    protected Board() {}
    
    protected Board(String name, String description, String createdBy, String updatedBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    @Builder
    protected Board(String name, String description, String createdBy, String updatedBy, boolean deleted, boolean enabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deleted = deleted;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void boardEnabled(boolean enabled, String updateBy) {
        this.enabled = enabled;
        this.updatedBy = updateBy;
        this.updatedAt = LocalDateTime.now();
    }

    
}
