package com.sergio.model.events.external;

import java.time.Instant;
import java.util.UUID;

public abstract class AccountKafkaEvent {

    private Instant instant;
    private String id;

    public AccountKafkaEvent() {
        this.instant = Instant.now();
        this.id = UUID.randomUUID().toString();
    }

    public AccountKafkaEvent(String id) {
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

}
