package com.study.kafka.controller;

import com.study.kafka.controller.model.Student;
import org.apache.kafka.common.requests.AbstractResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.study.kafka.configuration.KafkaSetup.runProducer;

@RestController("/students")
public class studentController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public static ResponseEntity createStudent(@RequestBody @Valid Student student){
        try {
            runProducer(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public static ResponseEntity getStudents(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
