package com.sipios.refactoring.model.enums;

public enum ItemType {
    TSHIRT (30, 1),
    DRESS (50, 0.8),
    JACKET (100, 0.9);

    private final int price;
    private final double salesDiscount;

    ItemType(int price, double salesDiscount) {
        this.price = price;
        this.salesDiscount = salesDiscount;
    }

    public double getPrice(boolean discountPeriod) {
        if (discountPeriod) {
            return price * salesDiscount;
        } else {
            return price;
        }
    }
}
