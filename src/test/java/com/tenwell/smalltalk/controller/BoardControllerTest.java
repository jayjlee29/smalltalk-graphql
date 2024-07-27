package com.tenwell.smalltalk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tenwell.smalltalk.data.http.BoardCreateRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void test001_게시판_생성() {
        webTestClient.post().uri("/api/board")
            .bodyValue(BoardCreateRequest.builder()
                .name("게시판 이름")
                .description("게시판 설명")
                .createdBy("생성자")
                .build())
            .exchange()
            .expectStatus().isOk();
    }


}
