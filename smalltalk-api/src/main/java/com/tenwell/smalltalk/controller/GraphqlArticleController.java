package com.tenwell.smalltalk.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.data.http.ArticleQueryRequest;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Article;
import com.tenwell.smalltalk.data.page.PageResult;
import com.tenwell.smalltalk.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Validated
@Controller
public class GraphqlArticleController {

    final private ArticleService articleService;

    @QueryMapping("getArticles")
    public Mono<TenwellResponse<PageResult<Article>>> getArticles(
        @ContextValue(name="session") TenwellSession session,
        @Argument(name="input") ArticleQueryRequest request) {
        
        log.info("Get Articles: {}", request);
        return articleService.getArticles(request)
            .doOnSuccess(articles -> log.info("Articles: {}", articles))
            .flatMap(articles -> TenwellResponse.ok(articles));
            
    }

}
