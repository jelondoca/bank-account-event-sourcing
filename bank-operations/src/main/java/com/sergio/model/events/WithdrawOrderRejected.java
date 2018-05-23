package com.sergio.model.events;

import com.sergio.model.OrderInfo;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class WithdrawOrderRejected extends Order {

    private final String reason;

    @JsonbCreator
    public WithdrawOrderRejected(@JsonbProperty("id") String orderToReject, @JsonbProperty("reason") String reason) {
        super(orderToReject);
        this.reason = reason;
    }

    @Override
    public void apply(OrderInfo order) {
        order.setInfo(reason);
        order.setStatus(OrderInfo.OrderStatusType.REJECTED);
    }

    public String getReason() {
        return reason;
    }
}
