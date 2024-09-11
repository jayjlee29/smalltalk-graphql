package com.tenwell.smalltalk.data.http;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class ArticleQueryRequest {
    @NotEmpty(message = "Board ID is required")
    private String boardId;
    private int pageNo;
    private int pageSize;

    protected ArticleQueryRequest() {
    }

    protected ArticleQueryRequest(String boardId, int pageNo, int pageSize) {
        this.boardId = boardId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
