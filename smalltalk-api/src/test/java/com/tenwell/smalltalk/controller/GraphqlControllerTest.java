package com.tenwell.smalltalk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphqlControllerTest {


    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Test
    void test001_게시글_가져오기() {

        graphQlTester
            .mutate()
            .header("Authorization", "USER dGVud2VsbA==")
            .build()
            .document("""
                query Article {
                getArticles(input: {
                    boardId: "66cad594ed126a5a4d22bd36",
                    pageNo: 1,
                    pageSize: 5
                }) {
                    code
                    message
                    data {
                    ...on PageResult {
                        pageNo
                        pageSize
                        totalCount
                        count
                        items {
                        ...on Article {
                            id
                            boardId
                            title
                            contents
                            createdBy
                            createdAt
                        }
                        }
                    }
                    }
                }
                }
            """)
            .execute()
            .path("data.getArticles.code").entity(Integer.class).isEqualTo(200)
            ;
    }

    @Test
    void test002_게시글_쓰기() {

        graphQlTester
            .mutate()
            .header("Authorization", "USER dGVud2VsbA==")
            .build()
            .document("""
                mutation {
                    createArticle (input: {
                        boardId: "66cad594ed126a5a4d22bd36"
                        title: "나의일기"
                        contents: "오늘은 토요일."
                        tags: ["주말"]
                    }) {
                        code
                        message
                        data {
                            ...on Article {
                                id
                                boardId
                                title
                                contents
                                createdBy
                                createdAt
                            }
                        }
                    }
                }
            """)
            .execute()
            .path("data.createArticle.code").entity(Integer.class).isEqualTo(200)
            ;
    }
}
