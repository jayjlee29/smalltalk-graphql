package com.tenwell.smalltalk.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.data.http.BoardCreateRequest;
import com.tenwell.smalltalk.data.http.BoardEnableRequest;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.exception.BoardNotFoundException;
import com.tenwell.smalltalk.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    final private BoardRepository boardRepository;

    public Mono<Board> createBoard(TenwellSession session, BoardCreateRequest request) {
        return boardRepository.save(Board.builder()
            .name(request.getName())
            .description(request.getDescription())
            .createdBy(session.getUserId())
            .updatedBy(session.getUserId())
            .enabled(request.getEnabled() == null ? false:request.getEnabled())
            .build());
    }
    

    public Mono<Boolean> checkBoardAvailable(String boardId) {
        return boardRepository.findById(boardId)
            .map(board -> board.checkBoardAvailable());
    }

    public Mono<Boolean> enableBoard(BoardEnableRequest request) {
        return boardRepository.findById(request.getBoardId())
            .switchIfEmpty(Mono.defer(()->Mono.error(new BoardNotFoundException(request.getBoardId()))))
            .map(board -> {
                board.boardEnabled(request.getEnabled(), request.getUpdatedBy());
                return board;
            })
            .flatMap(boardRepository::save)
            .map(board -> board.checkBoardAvailable());
    }

    public Mono<List<Board>> getBoards() {
        return boardRepository.findAll(Sort.by("_id").descending())
            .collectList();

    }

}
