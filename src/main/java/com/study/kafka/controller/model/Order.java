package com.study.kafka.controller.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Valid
public class Order {

    private int id;
    private String status;
    private String description;
    private Payment payment;

    public Order(){
    }

    private static final AtomicInteger count = new AtomicInteger(1);

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Payment getPayment() { return payment; }

    public Order(@NotNull int id, @NotNull String approved, @NotNull String description, Payment payment) {
        this.id = count.incrementAndGet();
        this.status = approved;
        this.description = description;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", description=" + description +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPayment(Payment payment) {this.payment = payment; };
}
