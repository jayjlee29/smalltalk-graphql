package com.tenwell.smalltalk.data.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.tenwell.smalltalk.data.enums.ArticleStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Document(collection = "article")
@Getter
@Builder
public class Article {

    @Id
    private String id;
    @Field("boardId")
    private String boardId;
    @Field("title")
    private String title;
    @Field("contents")
    private String contents;
    @Field("author")
    private String author;
    @Field("createdAt")
    private String createdAt;
    @Field("updatedAt")
    private String updatedAt;
    @Field("articleStatus")
    private ArticleStatus articleStatus; 
    @Field("catetoryList")
    private List<String> catetoryList;
    @Field("tagList")
    private List<Tag> tagList;

    protected Article() {}
    
    public Article(String id, String boardId, String title, String contents, String author, String createdAt, String updatedAt, ArticleStatus articleStatus, List<String> catetoryList, List<Tag> tagList) {
        this.id = id;
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.articleStatus = articleStatus;
        this.catetoryList = catetoryList;
        this.tagList = tagList;
    }

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
