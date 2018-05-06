package com.sergio.model;

import java.net.URI;
import java.util.List;
import java.util.Set;

public class OrderInfo {

    public enum OrderStatusType {
        PLACED, ACCEPTED, REJECTED
    }

    private OrderStatusType status;
    private String info;
    private Set<URI> _links;

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

    public Set<URI> get_links() {
        return _links;
    }

    public void set_links(Set<URI> _links) {
        this._links = _links;
    }

    @Override
    public String toString() {
        return "{\"OrderInfo\":{"
                + "\"status\":\"" + status + "\""
                + ", \"info\":\"" + info + "\""
                + "}}";
    }
}
