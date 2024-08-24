package com.tenwell.smalltalk.config.graphql;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class GraphqlTenwellStreamListener {
    private final StreamReceiver<String, MapRecord<String, String, String>> streamReceiver;

    public GraphqlTenwellStreamListener(ReactiveRedisConnectionFactory factory) {
        this.streamReceiver = StreamReceiver.create(factory);
    }

    public Flux<MapRecord<String, String, String>> receiveMessagesFromChannel(String channel) {
        return streamReceiver.receive(StreamOffset.fromStart(channel));
    }
}