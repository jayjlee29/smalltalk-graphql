package com.tenwell.smalltalk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tenwell.smalltalk.data.http.ArticleCreateRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        log.info("setup");
    }

    @Test
    void test001_게시글_쓰기() {
        log.info("게시글 쓰기 테스트");
        ArticleCreateRequest articleCreateRequest = ArticleCreateRequest.builder()
            .boardId("66a4328cae71903257170dc8")
            .title("제목")
            .contents("내용")
            .author("작성자")
            .tags(new String[] {"태그1", "태그2"})
            .build();

        webTestClient.post().uri("/api/article")
            .header("Authorization", "test")
            .bodyValue(articleCreateRequest)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("code", Integer.class).isEqualTo(200);
    }   

    @Test
    void test001_게시글_가져오기() {
        log.info("게시글 가져오기 테스트");
        webTestClient.get().uri("/api/article/1")
            .exchange()
            .expectStatus().isOk();
            
    }

}
