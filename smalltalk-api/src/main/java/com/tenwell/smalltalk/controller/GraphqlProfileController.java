package com.tenwell.smalltalk.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.tenwell.smalltalk.authorizer.TenwellSession;
import com.tenwell.smalltalk.data.Profile;
import com.tenwell.smalltalk.data.http.TenwellResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Validated
@Controller
public class GraphqlProfileController {
    
    @QueryMapping("getProfile")
    public Mono<TenwellResponse<Profile>> getProfile(@ContextValue(name="session") TenwellSession session) {
        
        var profile = Profile.builder()
                        .userId(session.getUserId())
                        .userName(session.getUserName())
                        .build();

        return TenwellResponse.ok(profile);
            
    }
}
