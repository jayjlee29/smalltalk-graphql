package com.tenwell.smalltalk.service;

import org.springframework.stereotype.Service;

import com.tenwell.smalltalk.data.http.BoardCreateRequest;
import com.tenwell.smalltalk.data.mongo.Board;
import com.tenwell.smalltalk.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BoardService {

    final private BoardRepository boardRepository;

    public Mono<Board> createBoard(BoardCreateRequest request) {
        return boardRepository.save(Board.builder()
            .name(request.getName())
            .description(request.getDescription())
            .createdBy(request.getCreatedBy())
            .updatedBy(request.getCreatedBy())
            .build());
    }

    public Mono<Boolean> checkBoardAvailable(String boardId) {
        return boardRepository.findById(boardId)
            .map(board -> board.checkBoardAvailable());
    }

}
