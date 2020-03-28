package com.study.kafka.configuration;

import com.study.kafka.controller.model.Student;
import com.study.kafka.dataprovider.StudentDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @Autowired
    private StudentDataProvider studentData;

    @KafkaListener(topics = "${topico-kafka}")
    public void receive(@Payload Student data,
                        @Headers MessageHeaders headers) {
        LOG.info("Recebido JSON='{}'", data);
        studentData.addStudent(data);
    }
}