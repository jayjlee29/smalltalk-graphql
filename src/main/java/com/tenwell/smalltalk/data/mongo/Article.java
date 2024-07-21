package com.tenwell.smalltalk.data.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tenwell.smalltalk.data.enums.ArticleStatus;

import lombok.Builder;
import lombok.Getter;

@Document
@Getter
@Builder
public class Article {

    @Id
    private String id;
    private String boardId;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
    private String updatedAt;
    private ArticleStatus articleStatus; 
    private List<String> catetoryList;
    private List<Comment> commentList;
    private List<Tag> tagList;

    public boolean publish() {
        if( ArticleStatus.DRAFT != this.articleStatus ) {
            return false;
        }

        this.articleStatus = ArticleStatus.PUBLISHED;
        return true;
    }


    public void update(Article newArticle) {
        this.title = newArticle.getTitle();
        this.contents = newArticle.getContents();
        this.updatedAt = newArticle.getUpdatedAt();
        this.articleStatus = newArticle.getArticleStatus();
        this.catetoryList = newArticle.getCatetoryList();
        this.tagList = newArticle.getTagList();
    }

}
