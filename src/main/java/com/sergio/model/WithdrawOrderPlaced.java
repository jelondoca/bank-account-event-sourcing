package com.sergio.model;

public class WithdrawOrderPlaced extends Order {

    private final String account;
    private final Float quantity;

    private WithdrawOrderPlaced(String account, Float quantity) {
        this.account = account;
        this.quantity = quantity;
    }

    public String getAccount() {
        return account;
    }

    public Float getQuantity() {
        return quantity;
    }

    @Override
    public void apply(OrderInfo order) {
        order.setStatus(OrderInfo.OrderStatusType.PLACED);
    }

    public static class WithdrawOrderPlacedBuilder {

        private String account;
        private Float quantity;

        public WithdrawOrderPlacedBuilder toAccount(String account) {
            this.account = account;
            return this;
        }

        public WithdrawOrderPlacedBuilder withQuantity(Float quantity) {
            this.quantity = quantity;
            return this;
        }

        public WithdrawOrderPlaced build() {
            return new WithdrawOrderPlaced(account, quantity);
        }

    }

}
