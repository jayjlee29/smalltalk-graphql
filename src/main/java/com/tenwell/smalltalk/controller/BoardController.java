package com.tenwell.smalltalk.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.data.http.BoardCreateRequest;
import com.tenwell.smalltalk.data.http.BoardEnableRequest;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.service.BoardService;
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
    public Mono<TenwellResponse<Board>> createBoard(TenwellSession session, @RequestBody BoardCreateRequest request) {
        log.info("createBoard");

        return boardService.createBoard(session, request)
            .doOnSuccess(board -> log.info("Board created: {}", board))
            .flatMap(board -> TenwellResponse.ok(board));
    }

    @PutMapping("/enable")
    public Mono<TenwellResponse<Boolean>> enableBoard(TenwellSession session, @RequestBody BoardEnableRequest request) {
        log.info("enableBoard {}", request);

        return boardService.enableBoard(request)
            .doOnSuccess(enabled -> log.info("Board enabled: {}", enabled))
            .flatMap(enabled -> TenwellResponse.ok(enabled));
    }


}
