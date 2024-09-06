package com.tenwell.smalltalk.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class GraphqlBoardController {

    final private BoardService boardService;

    @QueryMapping
    public Mono<TenwellResponse<List<Board>>> getBoards() {
        return boardService.getBoards()
            .doOnSuccess(boards -> log.info("Boards: {}", boards))
            .flatMap(boards -> TenwellResponse.ok(boards));
    }

}
