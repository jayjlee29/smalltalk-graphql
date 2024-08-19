package com.tenwell.smalltalk.authorizer;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
public class SimpleSessionToken implements TenwellSession{

    private String token;
    private String userId;
    private String userName;

    public SimpleSessionToken() {}
    public SimpleSessionToken(String token, String userId, String userName) {
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String identifier() {
        // TODO Auto-generated method stub
        return this.userId;
    }

    @Override
    public String getUserId() {
        // TODO Auto-generated method stub
        return this.userId;
    }

    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return this.userName;
    }

    @Override
    public void parse(String token) {
        // TODO Auto-generated method stub
        this.token = token;
        this.userId = token;
        this.userName = token;
    }
}
