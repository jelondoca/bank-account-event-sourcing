package com.sergio.services;

import com.sergio.model.OrderInfo;
import com.sergio.model.orders.Order;
import com.sergio.model.orders.WithdrawOrderAccepted;
import com.sergio.model.orders.WithdrawOrderPlaced;
import com.sergio.model.orders.WithdrawOrderRejected;
import com.sergio.repositories.EventStorage;

import javax.enterprise.event.Observes;
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
        System.out.println(orders);
        OrderInfo info = new OrderInfo();
        orders.forEach(o -> o.apply(info));
        return info;
    }

    private void whenWithdrawalRejected(@Observes WithdrawOrderRejected rejected) {
        eventStorage.add(rejected);
    }

    private void whenWithdrawalAccepted(@Observes WithdrawOrderAccepted rejected) {
        eventStorage.add(rejected);
    }
}
