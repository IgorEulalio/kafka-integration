package com.study.kafka.controller;

import com.study.kafka.configuration.KafkaProducer;
import com.study.kafka.controller.model.Student;
import com.study.kafka.dataprovider.StudentDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private StudentDataProvider studentDataProvider;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createStudent(@RequestBody @Valid Student student){
        //Enviando a mensagem para um tópico.
        producer.send(student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getStudents(){
        //O próprio tópico está incrementando o estudante dentro da fila
        return ResponseEntity.ok().body(studentDataProvider.getStudents());
    }
}
