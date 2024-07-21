package com.tenwell.smalltalk.exception;

public class TenwellException extends RuntimeException {

    public TenwellException(String message) {
        super(message);
    }

    public TenwellException(String message, Throwable cause) {
        super(message, cause);
    }

}
