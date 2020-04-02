package com.study.kafka.configuration;

import com.study.kafka.controller.model.Score;
import com.study.kafka.controller.model.Student;
import com.study.kafka.dataprovider.StudentDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerScore {

    private static final Logger LOG = LoggerFactory.getLogger(Student.class);

    @Autowired
    private StudentDataProvider studentData;

    @KafkaListener(topics = "${score-topic}", groupId = "json")
    public void receive(Score score) {
        LOG.info("Recebido notas do aluno de id {} no topico {} as notas serão inseridas nas informações do aluno.", score.getStudentId(), "SCORE_TOPIC");
        studentData.addScore(score);
    }
}
