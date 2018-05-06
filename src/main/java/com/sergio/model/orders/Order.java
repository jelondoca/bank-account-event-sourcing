package com.sergio.model.orders;

import com.sergio.model.OrderInfo;

import java.time.Instant;
import java.util.UUID;

public abstract class Order {

    private Instant instant;
    private String id;

    public Order() {
        this.instant = Instant.now();
        this.id = UUID.randomUUID().toString();
    }

    public Order(String id) {
        this.instant = Instant.now();
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getId() {
        return id;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void apply(OrderInfo order);

}
