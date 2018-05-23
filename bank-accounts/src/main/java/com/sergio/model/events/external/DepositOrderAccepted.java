package com.sergio.model.events.external;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class DepositOrderAccepted extends AccountKafkaEvent {

    @JsonbCreator
    public DepositOrderAccepted(@JsonbProperty("id") String orderAccepted) {
        super(orderAccepted);
    }

}
