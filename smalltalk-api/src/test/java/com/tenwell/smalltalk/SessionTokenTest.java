package com.tenwell.smalltalk;

import java.util.Base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.tenwell.smalltalk.authorizer.SimpleSessionToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionTokenTest {

    @Test
    void test001_토큰_생성() {
        // given
        final String userId = "tenwell";
    
        String base64Encoded = Base64.getEncoder().encodeToString(userId.getBytes());


        String token = "USER %s".format(base64Encoded);

        SimpleSessionToken sessionToken = new SimpleSessionToken();
        sessionToken.parse(token);

        log.info("token: {}, session {}", token, sessionToken);

        Assertions.assertEquals(userId, sessionToken.getUserId(), "userId가 일치하지 않습니다.");


    }
        

}
