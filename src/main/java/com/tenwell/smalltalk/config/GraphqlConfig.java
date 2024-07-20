package com.tenwell.smalltalk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;

@Configuration
public class GraphqlConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Long redisPort;

    @Bean
    public ReactiveRedisMessageListenerContainer messageListener(ReactiveRedisConnectionFactory factory) {

        return new ReactiveRedisMessageListenerContainer(factory);
    }


}
