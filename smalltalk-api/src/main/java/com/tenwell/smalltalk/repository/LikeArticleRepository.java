package com.tenwell.smalltalk.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.smalltalk.data.mongo.LikeArticle;

@Repository
public interface LikeArticleRepository  extends ReactiveMongoRepository<LikeArticle, String> {

    
}