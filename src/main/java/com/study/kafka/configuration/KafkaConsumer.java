package com.study.kafka.configuration;

import com.study.kafka.controller.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @KafkaListener(topics = "${app.topic.example}")
    public void receive(@Payload Student data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);
        System.out.println(data);
        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
    }
}