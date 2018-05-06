package com.sergio.com.sergio.repositories;

import com.sergio.model.Order;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class EventStorage {

    private static final Map<String, List<Order>> database = new ConcurrentHashMap<>();

    public void add(Order withdrawPlaced) {
        database.putIfAbsent(withdrawPlaced.getId().toString(), new ArrayList<>());
        database.computeIfPresent(withdrawPlaced.getId().toString(), (k,v) -> {
            v.add(withdrawPlaced);
            return v;
        });
    }

    public List<Order> get(String orderId) {
        return database.get(orderId);
    }
}
