package com.study.kafka.dataprovider;

import com.study.kafka.controller.model.Order;
import com.study.kafka.controller.model.Payment;
import com.study.kafka.gateway.OrderGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDataProvider implements OrderGateway {

    public List<Order> orders = new ArrayList<>();

    public List<Payment> payments = new ArrayList<>();

    public List<Order> getOrders(){
        return this.orders;
    }

    public void addOrder(Order order){
        order.setId(orders.size() + 1);
        order.setStatus("em analise");
        orders.add(order);
    }

    @Override
    public Order getOrderById(int id) {
        try{
            return orders.stream().filter(ord -> ord.getId() == id).findFirst().get();
        }
        catch (Exception ex){
            System.out.println("Problema ao encontrar a compra de id:" + id);
        }
        return null;
    }

    @Override
    public void addPayment(Payment payment) {
        this.payments.add(payment);
        this.getOrderById(payment.getOrderId()).setStatus("pagamento em análise");
        this.getOrderById(payment.getOrderId()).setPayment(payment);
    }

    @Override
    public List<Payment> getPayments() {
        return this.payments;
    }

}
