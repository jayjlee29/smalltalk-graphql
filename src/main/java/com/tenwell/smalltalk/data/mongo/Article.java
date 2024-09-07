package com.tenwell.smalltalk.data.mongo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.Update;

import com.tenwell.smalltalk.data.enums.ArticleStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Builder.Default;



@AllArgsConstructor
@RequiredArgsConstructor
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
    @Field("articleStatus")
    private ArticleStatus articleStatus; 
    @Field("catetoryList")
    private List<String> catetoryList;
    @Field("tagList")
    private List<Tag> tagList;

    @Field("createdAt") @CreatedDate
    private LocalDateTime createdAt;

    @Field("createdBy") @CreatedBy
    private String createdBy;

    @Field("updatedAt") @LastModifiedDate
    private LocalDateTime updatedAt;

    @Field("updatedBy") @LastModifiedBy
    private String updatedBy;

    // protected Article() {}

    // protected Article(String id, String boardId, String title, String contents, String author, ArticleStatus articleStatus,
    //         List<String> catetoryList, List<Tag> tagList, LocalDateTime createdAt, String createdBy,
    //         LocalDateTime updatedAt, String updatedBy) {
    //     this.id = id;
    //     this.boardId = boardId;
    //     this.title = title;
    //     this.contents = contents;
    //     this.author = author;
    //     this.articleStatus = articleStatus;
    //     this.catetoryList = catetoryList;
    //     this.tagList = tagList;
    //     this.createdAt = createdAt;
    //     this.createdBy = createdBy;
    //     this.updatedAt = updatedAt;
    //     this.updatedBy = updatedBy;
    // }



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
