package com.sergio.model.events.external;

import com.sergio.model.events.external.AccountKafkaEvent;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class DepositOrderPlaced extends AccountKafkaEvent {

    private final String accountId;
    private final Float quantity;

    @JsonbCreator
    public DepositOrderPlaced(@JsonbProperty("accountId") String accountId, @JsonbProperty("quantity") Float quantity) {
        this.accountId = accountId;
        this.quantity = quantity;
    }

    public String getAccountId() {
        return accountId;
    }

    public Float getQuantity() {
        return quantity;
    }

}
