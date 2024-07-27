package com.tenwell.smalltalk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenwell.smalltalk.data.http.BoardCreateRequest;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.service.BoardService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    final private BoardService boardService;

    @PostMapping
    public Mono<TenwellResponse<Board>> createBoard(@RequestBody BoardCreateRequest request) {
        log.info("createBoard");

        return boardService.createBoard(request)
            .doOnSuccess(board -> log.info("Board created: {}", board))
            .flatMap(board -> TenwellResponse.ok(board));
    }

}
