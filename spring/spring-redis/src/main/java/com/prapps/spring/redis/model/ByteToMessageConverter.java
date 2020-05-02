package com.prapps.spring.redis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class ByteToMessageConverter implements Converter<byte[], Message> {
    @Autowired
    private Jackson2JsonRedisSerializer<Message> deserializer;

    @Override
    public Message convert(byte[] source) {
        return deserializer.deserialize(source);
    }
}
