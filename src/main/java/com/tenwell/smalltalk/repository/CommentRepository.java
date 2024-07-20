package com.tenwell.smalltalk.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.tenwell.smalltalk.data.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

}
