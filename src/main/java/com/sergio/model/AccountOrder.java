package com.sergio.model;

public class AccountOrder {

    private String account;
    private Float quantity;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{\"AccountOrder\":{"
                + "\"account\":\"" + account + "\""
                + ", \"quantity\":\"" + quantity + "\""
                + "}}";
    }
}
