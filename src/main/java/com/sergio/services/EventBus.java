package com.sergio.services;

import com.sergio.model.Order;

public interface EventBus {
    void produce(Order order);
}
