package com.sipios.refactoring.service.exceptions;

import com.sipios.refactoring.model.enums.CustomerType;

public class PriceTooHighException extends Exception {

    public PriceTooHighException(double price, CustomerType customerType) {
        super("Price (" + price + ") is too high for " + customerType);
    }
}
