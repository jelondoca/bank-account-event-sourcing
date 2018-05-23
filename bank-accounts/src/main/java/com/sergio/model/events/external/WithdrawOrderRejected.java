package com.sergio.model.events.external;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderRejected extends AccountKafkaEvent {

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
}
