package com.tenwell.smalltalk.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.tenwell.smalltalk.data.enums.ArticleStatus;

import lombok.Builder;
import lombok.Getter;

@Document
@Getter
@Builder
public class Article {

    private String id;
    private String title;
    private String contents;
    private String author;
    private ArticleStatus articleStatus; 
    private List<String> catetoryList;
    private List<Comment> commentList;
    private List<Tag> tagList;
    private LocalDateTime publishedAt;
    private LocalDateTime unpublishedAt;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    

    public boolean publish() {
        if( ArticleStatus.DRAFT != this.articleStatus ) {
            return false;
        }

        this.articleStatus = ArticleStatus.PUBLISHED;
        return true;
    }


    public void updateArticle(Article newArticle) {
        this.title = newArticle.getTitle();
        this.contents = newArticle.getContents();
        this.updatedAt = newArticle.getUpdatedAt();
        this.articleStatus = newArticle.getArticleStatus();
        this.catetoryList = newArticle.getCatetoryList();
        this.tagList = newArticle.getTagList();
        this.updatedAt = LocalDateTime.now();
    }

}
