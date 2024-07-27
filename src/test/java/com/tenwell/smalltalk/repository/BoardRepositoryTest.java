package com.tenwell.smalltalk.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tenwell.smalltalk.data.mongo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void test001_게시판_확인(){
        log.info("게시판 확인 테스트");
        Board board = boardRepository.findById("66a4328cae71903257170dc8").block();

        log.info("board: {}", board);

        Assertions.assertNotNull(board);
            
    }

}
