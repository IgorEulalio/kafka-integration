package com.study.kafka.configuration;

import com.study.kafka.controller.model.Order;
import com.study.kafka.controller.model.Payment;
import com.study.kafka.dataprovider.OrderDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerPayment {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private OrderDataProvider orderData;

    @KafkaListener(topics = "${payment-topic}", groupId = "RecebePagamento")
    public void receive(Payment payment) {
        LOG.info("Recebido pagamento da compra {} no topico {}, na classe {}", orderData.getOrderById(payment.getOrderId()).getDescription() , "PAYMENT_TOPIC", KafkaConsumerPayment.class.getSimpleName());
        orderData.addPayment(payment);
    }
}
