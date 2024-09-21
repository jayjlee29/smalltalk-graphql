package com.tenwell.smalltalk.data.http;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ArticlePublishRequest {

    @NotBlank
    private String articleId;

    protected ArticlePublishRequest() {
    }

    protected ArticlePublishRequest(String articleId) {
        this.articleId = articleId;
    }

    

}
