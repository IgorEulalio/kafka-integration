package com.study.kafka.controller;

import com.study.kafka.configuration.KafkaProducer;
import com.study.kafka.controller.model.Payment;
import com.study.kafka.dataprovider.OrderDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    OrderDataProvider orderData;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPayment(@RequestBody @Valid Payment payment) {
        producer.send(payment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> getPayments() {

        return ResponseEntity.ok().body(orderData.getPayments());
    }

}
