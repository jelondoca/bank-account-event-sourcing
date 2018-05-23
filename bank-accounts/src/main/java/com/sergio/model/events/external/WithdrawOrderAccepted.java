package com.sergio.model.events.external;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderAccepted extends AccountKafkaEvent {

    @JsonbCreator
    public WithdrawOrderAccepted(@JsonbProperty("id") String orderAccepted) {
        super(orderAccepted);
    }

}
