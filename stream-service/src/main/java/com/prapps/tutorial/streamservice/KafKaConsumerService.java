package com.prapps.tutorial.streamservice;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.prapps.tutorial.streamservice.consts.AppConstants.Constants.GROUP_ID_CONST;
import static com.prapps.tutorial.streamservice.consts.AppConstants.Constants.INPUT_TOPIC_NAME;

@Service
public class KafKaConsumerService {
    private final Logger logger =
            LoggerFactory.getLogger(KafKaConsumerService.class);

    @KafkaListener(topics = INPUT_TOPIC_NAME, groupId = GROUP_ID_CONST)
    public void consume(String message)
    {
        logger.info(String.format("Message recieved -> %s", message));
    }
}
