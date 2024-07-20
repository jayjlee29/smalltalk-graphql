package com.tenwell.graphql.sample.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.tenwell.graphql.sample.data.Article;
import com.tenwell.graphql.sample.data.Comment;
import com.tenwell.graphql.sample.data.Tag;
import com.tenwell.graphql.sample.data.enums.ArticleStatus;
import com.tenwell.graphql.sample.data.http.ArticleCreateRequest;
import com.tenwell.graphql.sample.exception.ArticlePublishException;
import com.tenwell.graphql.sample.repository.ArticleRepository;
import com.tenwell.graphql.sample.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    final private ArticleRepository articleRepository;
    final private CommentRepository commentRepository;


    public Mono<Article> getArticle(String id) {
        return articleRepository.findById(id);
    }

    public Mono<Article> writeArticle(ArticleCreateRequest request) {

        List<Tag> tagList = request.getTags() == null? new ArrayList<>() : Stream.of(request.getTags())
                        .map(tag -> Tag.builder().name(tag).build())
                        .toList();

        Article article = Article.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .tagList(tagList)
                .build();
        log.info("create article: {}", article);
        return articleRepository.save(article);
    }

    public Mono<Article> updateArticle(String articleId, Article newArticle) {
        return articleRepository.findById(articleId)
                .map(article -> {
                    article.update(newArticle);
                    return article;
                })
                .flatMap(articleRepository::save);
    }

    public Mono<Article> publishArticle(String articleId) {
        return articleRepository.findById(articleId)
                .map(article -> {
                    
                    if(!article.publish()) {
                        throw new ArticlePublishException("Article is already published");
                    }

                    return article;
                })
                .flatMap(articleRepository::save);
    }

    public Mono<Article> wrtieComment(String articleId, Comment newComment) {

        return articleRepository.findById(articleId)
                .flatMap(article -> {
                    if( ArticleStatus.PUBLISHED != article.getArticleStatus() ) {
                        return Mono.error(new RuntimeException("Cannot write comment to draft article"));
                    }

                    newComment.updateArticleId(articleId);

                    return commentRepository.save(newComment)
                            .then(Mono.defer(() -> {
                                return articleRepository.findById(articleId);
                            }));
                });
    }

}
