package com.sergio.model;

import java.time.Instant;
import java.util.UUID;

public abstract class Order {

    private final Instant instant;
    private final UUID id;

    public Order() {
        this.instant = Instant.now();
        this.id = UUID.randomUUID();
    }

    public Instant getInstant() {
        return instant;
    }

    public UUID getId() {
        return id;
    }

    public abstract void apply(OrderInfo order);

}
