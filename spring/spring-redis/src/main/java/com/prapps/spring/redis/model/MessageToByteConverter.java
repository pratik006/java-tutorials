package com.prapps.spring.redis.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class MessageToByteConverter implements Converter<Message, byte[]>, RedisSerializer<Message> {
    private Jackson2JsonRedisSerializer<Message> serializer;

    @Autowired
    public MessageToByteConverter(Jackson2JsonRedisSerializer<Message> serializer) {
        this.serializer = serializer;
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public byte[] convert(Message source) {
        return serializer.serialize(source);
    }

    @Override
    public byte[] serialize(Message message) throws SerializationException {
        return serializer.serialize(message);
    }

    @Override
    public Message deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}
