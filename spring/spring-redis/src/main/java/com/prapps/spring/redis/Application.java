package com.prapps.spring.redis;

import com.prapps.spring.redis.model.Message;
import com.prapps.spring.redis.model.MessageToByteConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        String queueName = "messageList";
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Application.class);
        ctx.refresh();
        RedisTemplate<String, Message> redisTemplate = ctx.getBean("redisTemplate", RedisTemplate.class);
        ListOperations<String, Message> lops = redisTemplate.opsForList();

        Message msg1 = new Message("pratik", "hello");
        Message msg2 = new Message("barsha", "hello2");
        lops.leftPush(queueName, msg1);
        lops.leftPush(queueName, msg2);
        System.out.println(lops.size(queueName));
        Message msg = lops.rightPop(queueName);
        System.out.println(msg.getSubject() + " " +msg.getFrom());
        msg = lops.rightPop(queueName);
        System.out.println(msg.getSubject() + " " +msg.getFrom());
        lops.getOperations().delete(queueName);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        return jedisConFactory;
    }

    @Bean @Autowired
    public RedisTemplate<Integer, Message> redisTemplate(JedisConnectionFactory connectionFactory, MessageToByteConverter messageToByteConverter) {
        final RedisTemplate<Integer, Message> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
        //template.setValueSerializer(messageToByteConverter);
        template.setValueSerializer(new JsonRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.setEnableDefaultSerializer(false);
        return template;
    }

    @Bean
    public Jackson2JsonRedisSerializer create() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Message.class);
        return jackson2JsonRedisSerializer;
    }
}
