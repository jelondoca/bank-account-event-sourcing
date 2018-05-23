package com.sergio.model.events.internal;

import com.sergio.model.AccountInfo;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderRejected extends AccountEvent {

    private final String reason;
    private final String id;

    @JsonbCreator
    public WithdrawOrderRejected(@JsonbProperty("id") String orderToReject, @JsonbProperty("reason") String reason) {
        this.id = orderToReject;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getId() {
        return id;
    }

    @Override
    public void apply(AccountInfo account) {
        // nothing to do?
    }
}
