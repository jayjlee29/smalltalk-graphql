package com.tenwell.graphql.sample.data;

import java.util.List;

import com.tenwell.graphql.sample.data.enums.ArticleStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Article {

    private String id;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
    private String updatedAt;
    private ArticleStatus articleStatus; 
    private List<String> catetoryList;
    private List<Comment> commentList;
    private List<Tag> tagList;


}
