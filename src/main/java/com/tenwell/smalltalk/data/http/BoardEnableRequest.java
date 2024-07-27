package com.tenwell.smalltalk.data.http;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BoardEnableRequest {

    private String boardId;
    private Boolean enabled;
    private String updatedBy;

    protected BoardEnableRequest() {}

    protected BoardEnableRequest(String boardId, Boolean enabled, String updatedBy) {
        this.boardId = boardId;
        this.enabled = enabled;
        this.updatedBy = updatedBy;
    }

}
