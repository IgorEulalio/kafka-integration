package com.study.kafka.controller;

import com.study.kafka.configuration.KafkaProducer;
import com.study.kafka.controller.model.Score;
import com.study.kafka.dataprovider.StudentDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/scores")
@RestController
public class ScoreController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    StudentDataProvider studentData;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addScore(@RequestBody @Valid Score score) {
        producer.send(score);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Score>> getScores() {

        return ResponseEntity.ok().body(studentData.getScores());
    }

}
