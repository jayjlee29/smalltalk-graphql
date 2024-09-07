package com.tenwell.smalltalk.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.smalltalk.data.mongo.Article;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {

    Flux<Article> findByBoardId(String boardId, Pageable pageable);
    Mono<Long> countByBoardId(String boardId);
}
