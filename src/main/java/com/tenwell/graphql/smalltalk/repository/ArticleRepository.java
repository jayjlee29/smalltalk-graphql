package com.tenwell.graphql.smalltalk.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.graphql.smalltalk.data.mongo.Article;

@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {

}
