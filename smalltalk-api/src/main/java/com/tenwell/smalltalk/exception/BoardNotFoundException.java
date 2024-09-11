package com.tenwell.smalltalk.exception;

import com.tenwell.smalltalk.controller.TenwellController;

public class BoardNotFoundException extends TenwellException{

    private String boardId;
    public BoardNotFoundException(String boardId) {
        super("Board not found: " + boardId);
        this.boardId = boardId;
    }
}
