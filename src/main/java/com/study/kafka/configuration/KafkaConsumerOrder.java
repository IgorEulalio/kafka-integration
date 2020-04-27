package com.study.kafka.configuration;

import com.study.kafka.controller.model.Order;
import com.study.kafka.dataprovider.OrderDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerOrder {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private OrderDataProvider orderData;

    @KafkaListener(topics = "${order-topic}", groupId = "json")
    public void receive(Order data) {
        LOG.info("Recebido compra: {} no controller {}", data.getDescription(), KafkaConsumerOrder.class.getSimpleName());
        orderData.addOrder(data);
    }
}