package com.tenwell.smalltalk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.client.ArticleLikePublisher;
import com.tenwell.smalltalk.data.http.ArticleCreateRequest;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Article;
import com.tenwell.smalltalk.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    final private ArticleService articleService;

    final private ArticleLikePublisher articleLikePublisher;

    @GetMapping("/sliding")
    public Flux<Article> sliding() {
        log.info("sliding");
        return Flux.empty();
    }

    @GetMapping("page")
    public Mono<List<Article>> page() {

        return Mono.empty();
    }

    @GetMapping("/{articleCanonicalId}")
    public Mono<TenwellResponse<Article>> detail(@PathVariable("articleCanonicalId") String articleCanonical) {
        log.info("detail: {}", articleCanonical);
        return Mono.empty();
    }

    @PostMapping
    public Mono<TenwellResponse<Article>> write(@RequestBody ArticleCreateRequest request, TenwellSession session) {
        log.info("write: {} {}", request, session);
        return articleService.writeArticle(session, request)
            .doOnSuccess(article -> log.info("Article created: {}", article))
            .flatMap(article -> TenwellResponse.ok(article));
    }

    @PostMapping("/update")
    public Mono<TenwellResponse<Article>> update() {

        return Mono.empty();
    }

    @PostMapping("/publish")
    public Mono<TenwellResponse<Article>> publish() {

        return Mono.empty();
    }

    @PostMapping("/unpublish")
    public Mono<TenwellResponse<Article>> unpublish() {

        return Mono.empty();
    } 

    @DeleteMapping("/delete")
    public Mono<TenwellResponse<Boolean>> delete() {

        return Mono.empty();
    }

    @PutMapping("/{articleId}/like")
    public Mono<TenwellResponse<Boolean>> like(@PathVariable("articleId") String articleId, TenwellSession session) {
        log.info("like: {} {}", articleId, session);

        return articleService.like(articleId, session)
            .doOnSuccess(liked -> articleLikePublisher.publish(articleId, session.getUserId()))
            .flatMap(article -> TenwellResponse.ok(true));

    }

}
