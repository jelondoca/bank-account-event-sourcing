package com.sergio.model.events.internal;

import com.sergio.model.AccountInfo;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class DepositOrderAccepted extends AccountEvent {

    private final float quantity;

    @JsonbCreator
    public DepositOrderAccepted(@JsonbProperty("id") String orderAccepted, float quantity) {
        super(orderAccepted);
        this.quantity = quantity;
    }

    @Override
    public void apply(AccountInfo account) {
        account.setQuantity(account.getQuantity() + quantity);
    }

}
