package com.sergio.model.events.internal;

import com.sergio.model.AccountInfo;

import java.time.Instant;
import java.util.UUID;

public abstract class AccountEvent {
    private Instant instant;
    private String id;

    public AccountEvent() {
        this.instant = Instant.now();
        this.id = UUID.randomUUID().toString();
    }

    public AccountEvent(String id) {
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
    public abstract void apply(AccountInfo account);
}
