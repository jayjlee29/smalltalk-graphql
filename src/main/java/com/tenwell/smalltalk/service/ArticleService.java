package com.tenwell.smalltalk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.tenwell.smalltalk.data.mongo.Article;
import com.tenwell.smalltalk.data.mongo.Comment;
import com.tenwell.smalltalk.data.mongo.Tag;
import com.tenwell.smalltalk.data.enums.ArticleStatus;
import com.tenwell.smalltalk.data.http.ArticleCreateRequest;
import com.tenwell.smalltalk.exception.ArticlePublishException;
import com.tenwell.smalltalk.exception.TenwellException;
import com.tenwell.smalltalk.repository.ArticleRepository;
import com.tenwell.smalltalk.repository.BoardRepository;
import com.tenwell.smalltalk.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    final private ArticleRepository articleRepository;
    final private CommentRepository commentRepository;
    final private BoardRepository boardRepository;


    public Mono<Article> getArticle(String id) {
        return articleRepository.findById(id);
    }

    public Mono<Article> writeArticle(ArticleCreateRequest request) {

        return checkBoardWriteable(request.getBoardId())
            .flatMap(isWriteable -> {

                if(isWriteable == false) {
                    return Mono.error(new TenwellException("Board is not writable"));
                }
                
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
            });
    }

    private Mono<Boolean> checkBoardWriteable(String boardId) {
        return boardRepository.findById(boardId)
            .switchIfEmpty(Mono.error(new TenwellException("Board not found")))
            .flatMap(board -> {
                return Mono.just(board.writable());
            });
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