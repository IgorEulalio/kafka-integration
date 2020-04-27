package com.study.kafka.gateway;

import com.study.kafka.controller.model.Order;
import com.study.kafka.controller.model.Payment;

import java.util.List;

public interface OrderGateway {
    List<Order> getOrders();
    List<Payment> getPayments();

    void addOrder(Order student);
    void addPayment(Payment payment);

    Order getOrderById(int id);
}
