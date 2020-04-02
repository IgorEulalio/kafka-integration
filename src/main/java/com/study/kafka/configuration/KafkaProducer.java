package com.study.kafka.configuration;
import com.study.kafka.controller.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;
import java.util.UUID;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    @Value("${topico-kafka}")
    private String topic;

    public void send(Student data) {
        ListenableFuture<SendResult<String, Student>> future =
                kafkaTemplate.send(topic, String.valueOf(UUID.randomUUID()), data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Student>>() {

            @Override
            public void onSuccess(SendResult<String, Student> result) {
                LOG.info("Enviado o aluno {} para a o topico {} e na partição {}", data.toString(), result.getRecordMetadata().topic(), result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + data.toString() + "] due to : " + ex.getMessage());
            }
        });


    }
}
