package com.sipios.refactoring.service;

import com.sipios.refactoring.model.CustomerBasket;
import com.sipios.refactoring.model.Item;
import com.sipios.refactoring.service.exceptions.PriceTooHighException;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class ShoppingService {

    public double computePrice(CustomerBasket customerBasket) throws PriceTooHighException {
        if (customerBasket.items().isEmpty()) {
            return 0;
        }

        double price = 0;
        double customerDiscount = customerBasket.type().getDiscount();
        boolean discountPeriod = isDiscountPeriod();

        for (Item item : customerBasket.items()) {
            price += item.type().getPrice(discountPeriod) * item.nb() * customerDiscount;
        }

        if (price > customerBasket.type().getPaymentLimit()) {
            throw new PriceTooHighException(price, customerBasket.type());
        }

        return price;
    }

    // FIXME add test with mocked clock
    private boolean isDiscountPeriod() {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("Europe/Paris"));

        return (date.getDayOfMonth() < 15 && date.getDayOfMonth() > 5 && date.getMonth() == Month.JUNE)
            || (date.getDayOfMonth() < 15 && date.getDayOfMonth() > 5 && date.getMonth() == Month.JANUARY);
    }
}
