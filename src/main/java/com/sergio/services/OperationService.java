package com.sergio.services;

import com.sergio.repositories.EventStorage;
import com.sergio.model.Order;
import com.sergio.model.OrderInfo;
import com.sergio.model.WithdrawOrderPlaced;

import javax.inject.Inject;
import java.util.List;

public class OperationService {

    @Inject
    EventStorage eventStorage;

    @Inject
    EventBus eventBus;

    public String withdraw(String account, Float quantity) {
        WithdrawOrderPlaced withdrawPlaced = new WithdrawOrderPlaced(account, quantity);
        eventStorage.add(withdrawPlaced);
        eventBus.produce(withdrawPlaced);
        return withdrawPlaced.getId();
    }

    public OrderInfo check(String orderId) {
        List<Order> orders = eventStorage.get(orderId);
        OrderInfo info = new OrderInfo();
        orders.forEach(o -> o.apply(info));
        return info;
    }
}
