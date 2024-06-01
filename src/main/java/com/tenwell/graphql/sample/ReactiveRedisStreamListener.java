package com.tenwell.graphql.sample;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class ReactiveRedisStreamListener {
    private final StreamReceiver<String, MapRecord<String, String, String>> streamReceiver;

    public ReactiveRedisStreamListener(ReactiveRedisConnectionFactory factory) {
        this.streamReceiver = StreamReceiver.create(factory);
    }

    public Flux<MapRecord<String, String, String>> receiveMessagesFromChannel(String channel) {
        return streamReceiver.receive(StreamOffset.fromStart(channel));
    }
}