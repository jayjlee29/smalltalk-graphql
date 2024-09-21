package com.tenwell.smalltalk.data.http;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

@ToString
@Data
@Builder
public class ArticleCreateRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String boardId;
    @NotEmpty
    private String contents;
    private List<String> tags;

    protected ArticleCreateRequest() {}

    protected ArticleCreateRequest(String title, String boardId, String contents, List<String> tags) {
        this.title = title;
        this.boardId = boardId;
        this.contents = contents;
        this.tags = tags;
    }
}
