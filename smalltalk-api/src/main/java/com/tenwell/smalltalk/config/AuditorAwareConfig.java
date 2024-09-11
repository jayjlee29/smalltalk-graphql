package com.tenwell.smalltalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import com.tenwell.smalltalk.authorizer.TenwellSession;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import com.tenwell.smalltalk.authorizer.SimpleSessionToken;
@Slf4j
@Configuration
public class AuditorAwareConfig {

    /**
     * MongoDB JPA AuditorAware
     * CreatedBy, UpdatedBy를 위한 AuditorAware
     */
    @Bean
    public ReactiveAuditorAware<String> auditorProvider() {
        return () -> {
            return Mono.deferContextual(ctx -> {
                log.info("session: {}", ctx.get(SimpleSessionToken.SESSION_KEY).toString());
                if (ctx.hasKey("session")) {
                    TenwellSession session = ctx.get("session");
                    return Mono.just(session.getUserId());
                }
                return Mono.just("anonymous");
            });
        };
    }
}