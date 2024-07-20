package com.tenwell.graphql.sample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.graphql.sample.data.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

}
