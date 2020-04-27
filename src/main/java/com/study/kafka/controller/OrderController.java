package com.study.kafka.controller;

import com.study.kafka.configuration.KafkaProducer;
import com.study.kafka.controller.model.Order;
import com.study.kafka.dataprovider.OrderDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private OrderDataProvider orderDataProvider;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@RequestBody @Valid Order order){
        //Enviando a mensagem para um tópico.
        producer.send(order);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrders(){
        //O próprio tópico está incrementando o estudante dentro da fila
        return ResponseEntity.ok().body(orderDataProvider.getOrders());
    }
}
