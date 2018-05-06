package com.sergio.model;

public class OrderInfo {

    public enum OrderStatusType {
        PLACED, ACCEPTED, REJECTED
    }

    private OrderStatusType status;

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }
}
