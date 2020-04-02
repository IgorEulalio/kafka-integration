package com.study.kafka;

import com.study.kafka.configuration.KafkaConsumer;
import com.study.kafka.controller.model.Student;
import com.study.kafka.dataprovider.StudentDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @Autowired
    private StudentDataProvider studentData;

    @KafkaListener(topics = "${topico-kafka}", groupId = "json")
    public void receive(Student data) {
        LOG.info("Recebido aluno {} no controller {}", data.getName(), KafkaConsumer.class.getSimpleName());
        studentData.addStudent(data);
    }
}
