package com.sergio.services;

import com.sergio.model.orders.Order;

public interface EventBus {
    void produce(Order order);
}
