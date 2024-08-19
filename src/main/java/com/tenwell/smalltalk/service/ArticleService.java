package com.tenwell.smalltalk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.tenwell.smalltalk.data.mongo.Article;
import com.tenwell.smalltalk.data.mongo.Comment;
import com.tenwell.smalltalk.data.mongo.LikeArticle;
import com.tenwell.smalltalk.data.mongo.Tag;
import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.data.enums.ArticleStatus;
import com.tenwell.smalltalk.data.http.ArticleCreateRequest;
import com.tenwell.smalltalk.exception.ArticleNotFoundException;
import com.tenwell.smalltalk.exception.ArticlePublishException;
import com.tenwell.smalltalk.exception.TenwellException;
import com.tenwell.smalltalk.repository.ArticleRepository;
import com.tenwell.smalltalk.repository.BoardRepository;
import com.tenwell.smalltalk.repository.CommentRepository;
import com.tenwell.smalltalk.repository.LikeArticleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    final private ArticleRepository articleRepository;
    final private LikeArticleRepository likeArticleRepository;
    final private CommentRepository commentRepository;
    final private BoardRepository boardRepository;


    public Mono<Article> getArticle(String id) {
        return articleRepository.findById(id);
    }

    public Mono<Article> writeArticle(TenwellSession session, ArticleCreateRequest request) {

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
                        .articleStatus(ArticleStatus.DRAFT)
                        .author(session.getUserId())
                        .boardId(request.getBoardId())
                        .catetoryList(new ArrayList<>())
                        .createdAt(request.getAuthor())
                        .updatedAt(request.getAuthor())
                        .build();
                log.info("create article: {}", article);
                return articleRepository.save(article);
            });
    }

    private Mono<Boolean> checkBoardWriteable(String boardId) {
        if(boardId == null) {
            return Mono.error(new TenwellException("Board Id is Empty"));
        }
        return boardRepository.findById(boardId)
            .switchIfEmpty(Mono.error(new TenwellException("Board not found")))
            .flatMap(board -> {
                return Mono.just(board.checkBoardAvailable());
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

    public Mono<Boolean> like(String articleId, TenwellSession session) {
        return articleRepository.findById(articleId)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ArticleNotFoundException(articleId))))
            .map(article -> {
                LikeArticle likeArticle = LikeArticle.builder()
                    .articleId(articleId)
                    .userId(session.getUserId())
                    .build();
                
                return likeArticle;            
            })
            .flatMap(likeArticleRepository::save)
            .map(likeArticle -> Boolean.TRUE);
        }

}
