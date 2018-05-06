package com.sergio.model.orders;

import com.sergio.model.OrderInfo;

public class WithdrawOrderAccepted extends Order {
    @Override
    public void apply(OrderInfo order) {
        order.setStatus(OrderInfo.OrderStatusType.ACCEPTED);
    }
}
