package com.sergio.model;

public class OrderInfo {

    public enum OrderStatusType {
        PLACED, ACCEPTED, REJECTED
    }

    private OrderStatusType status;
    private String info;

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "{\"OrderInfo\":{"
                + "\"status\":\"" + status + "\""
                + ", \"info\":\"" + info + "\""
                + "}}";
    }
}
