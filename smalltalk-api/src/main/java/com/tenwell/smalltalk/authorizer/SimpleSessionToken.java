package com.tenwell.smalltalk.authorizer;
import java.util.Base64;

import lombok.ToString;

@ToString
public class SimpleSessionToken implements TenwellSession{

    final static public String SESSION_KEY = "session";

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
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedToken = new String(decoder.decode(token));
        decodedToken = decodedToken.replace("USER ", "");
        this.userId = token;
        this.userName = token;
    }

    public static TenwellSession anonymous() {
        return new SimpleSessionToken(null, "anonymous", "anonymous");
    }
}
