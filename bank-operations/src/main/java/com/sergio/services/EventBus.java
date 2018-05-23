package com.sergio.services;

import com.sergio.model.events.Order;

public interface EventBus {
    void produce(Order order);
}
