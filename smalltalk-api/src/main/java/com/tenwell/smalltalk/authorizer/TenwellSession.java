package com.tenwell.smalltalk.authorizer;

public interface TenwellSession {

    String identifier();

    String getUserId();

    String getUserName();

    void parse(String token);
}
