package com.sergio.model.events.external;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class DepositOrderAcceptedEvent extends AccountKafkaEvent {

    @JsonbCreator
    public DepositOrderAcceptedEvent(@JsonbProperty("id") String orderAccepted) {
        super(orderAccepted);
    }

}
