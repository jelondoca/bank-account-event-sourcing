package com.sergio.services;

import com.sergio.model.events.external.AccountKafkaEvent;

public interface EventBus {
    void produce(AccountKafkaEvent order);
}
