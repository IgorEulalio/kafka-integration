package com.study.kafka.configuration;
import com.study.kafka.controller.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    @Value("${topico-kafka}")
    private String topic;

    public void send(Student data) {
        LOG.info("Enviando JSON='{}' para o tópico='{}'", data, topic);

        Message<Student> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
    }
}
