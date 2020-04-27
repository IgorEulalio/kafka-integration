package com.study.kafka.configuration;

import com.study.kafka.controller.model.Order;
import com.study.kafka.controller.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplateOrder;

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplatePayment;

    @Value("${order-topic}")
    private String orderTopic;

    @Value("${payment-topic}")
    private String paymentTopic;

    public void send(Order data) {

        ListenableFuture<SendResult<String, Order>> future =
                kafkaTemplateOrder.send(orderTopic, String.valueOf(UUID.randomUUID()), data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {

            @Override
            public void onSuccess(SendResult<String, Order> result) {
                LOG.info("Enviado a compra: {} para a o topico {} e na partição {}", data.getDescription(), result.getRecordMetadata().topic(), result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + data.toString() + "] due to : " + ex.getMessage());
            }
        });
    }

    public void send(Payment payment) {

        ListenableFuture<SendResult<String, Payment>> future =
                kafkaTemplatePayment.send(paymentTopic, String.valueOf(UUID.randomUUID()), payment);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Payment>>() {

            @Override
            public void onSuccess(SendResult<String, Payment> result) {
                LOG.info("Enviado o pagamento: {} para a o topico {} e na partição {}", payment.toString(), result.getRecordMetadata().topic(), result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + payment.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}
