package com.tenwell.smalltalk.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.smalltalk.data.Article;

@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {

}
