package com.sergio.model.events.internal;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.external.AccountKafkaEvent;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderAccepted extends AccountEvent {

    private final float quantity;

    @JsonbCreator
    public WithdrawOrderAccepted(@JsonbProperty("id") String orderAccepted, float quantity) {
        super(orderAccepted);
        this.quantity = quantity;
    }

    @Override
    public void apply(AccountInfo account) {
        account.setQuantity(account.getQuantity() - quantity);
    }

    public float getQuantity() {
        return quantity;
    }
}
