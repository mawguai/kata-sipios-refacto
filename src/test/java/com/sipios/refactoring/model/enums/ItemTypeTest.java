package com.sipios.refactoring.model.enums;

import org.junit.jupiter.api.Test;

import static com.sipios.refactoring.model.enums.ItemType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTypeTest {

    @Test
    void should_return_discount_price_dress() {
        assertEquals(DRESS.getPrice(true), 40);
    }

    @Test
    void should_return_normal_dress() {
        assertEquals(DRESS.getPrice(false), 50);
    }

    @Test
    void should_return_discount_price_tshirt() {
        assertEquals(TSHIRT.getPrice(true), 30);
    }

    @Test
    void should_return_normal_tshirt() {
        assertEquals(TSHIRT.getPrice(false), 30);
    }

    @Test
    void should_return_discount_price_jacket() {
        assertEquals(JACKET.getPrice(true), 90);
    }

    @Test
    void should_return_normal_jacket() {
        assertEquals(JACKET.getPrice(false), 100);
    }
}
