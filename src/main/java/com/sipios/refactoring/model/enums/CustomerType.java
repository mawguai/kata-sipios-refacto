package com.sipios.refactoring.model.enums;

public enum CustomerType {
    STANDARD_CUSTOMER (1, 200),
    PREMIUM_CUSTOMER (0.9, 800),
    PLATINUM_CUSTOMER (0.5, 2000);

    private final double discount;
    private final double paymentLimit;

    CustomerType(double discount, double paymentLimit) {
        this.discount = discount;
        this.paymentLimit = paymentLimit;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPaymentLimit() {
        return paymentLimit;
    }
}
