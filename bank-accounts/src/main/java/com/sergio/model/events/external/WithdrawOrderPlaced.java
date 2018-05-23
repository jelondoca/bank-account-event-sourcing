package com.sergio.model.events.external;

import com.sergio.model.events.external.AccountKafkaEvent;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderPlaced extends AccountKafkaEvent {

    private final String account;
    private final Float quantity;

    @JsonbCreator
    public WithdrawOrderPlaced(@JsonbProperty("account") String account, @JsonbProperty("quantity") Float quantity) {
        this.account = account;
        this.quantity = quantity;
    }

    public String getAccount() {
        return account;
    }

    public Float getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "{\"WithdrawOrderPlaced\":{"
                + "\"account\":\"" + account + "\""
                + ", \"quantity\":\"" + quantity + "\""
                + "}}";
    }
}
