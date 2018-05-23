package com.sergio.repositories;

import com.sergio.model.events.Order;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class OperationEventStorage {

    private static final Map<String, List<Order>> database = new ConcurrentHashMap<>();

    public void add(Order order) {
        database.putIfAbsent(order.getId(), new ArrayList<>());
        database.computeIfPresent(order.getId(), (k, v) -> {
            v.add(order);
            return v;
        });
    }

    public List<Order> get(String orderId) {
        return database.get(orderId);
    }

    public List<Order> getAll(String orderId) {
        return database.get(orderId);
    }
}
