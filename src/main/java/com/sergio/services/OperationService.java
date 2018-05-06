package com.sergio.services;

import com.sergio.model.OrderInfo;
import com.sergio.model.events.DepositOrderAccepted;
import com.sergio.model.events.DepositOrderPlaced;
import com.sergio.model.events.Order;
import com.sergio.model.events.WithdrawOrderAccepted;
import com.sergio.model.events.WithdrawOrderPlaced;
import com.sergio.model.events.WithdrawOrderRejected;
import com.sergio.repositories.EventStorage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

public class OperationService {

    @Inject
    EventStorage eventStorage;

    @Inject
    EventBus eventBus;

    public String withdraw(String accountId, Float quantity) {
        WithdrawOrderPlaced withdrawPlaced = new WithdrawOrderPlaced(accountId, quantity);
        eventStorage.add(withdrawPlaced);
        eventBus.produce(withdrawPlaced);
        return withdrawPlaced.getId();
    }

    public String deposit(String accountId, Float quantity) {
        DepositOrderPlaced depositOrder = new DepositOrderPlaced(accountId, quantity);
        eventStorage.add(depositOrder);
        eventBus.produce(depositOrder);
        return depositOrder.getId();
    }

    public OrderInfo check(String orderId) {
        List<Order> orders = eventStorage.get(orderId);
        System.out.println(orders);
        OrderInfo info = new OrderInfo();
        orders.forEach(o -> o.apply(info));
        return info;
    }

    public List<Order> allOrders(String orderId) {
        return eventStorage.getAll(orderId);
    }

    private void whenWithdrawalRejected(@Observes WithdrawOrderRejected rejected) {
        eventStorage.add(rejected);
    }

    private void whenWithdrawalAccepted(@Observes WithdrawOrderAccepted accepted) {
        eventStorage.add(accepted);
    }

    private void whenDepositAccepted(@Observes DepositOrderAccepted accepted) {
        System.out.println("DEPOSIT ACCEPTEDDDD!!!!!");
        eventStorage.add(accepted);
    }
}
