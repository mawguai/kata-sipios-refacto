package com.sipios.refactoring.controller;

import com.sipios.refactoring.model.CustomerBasket;
import com.sipios.refactoring.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.sipios.refactoring.model.enums.CustomerType.*;
import static com.sipios.refactoring.model.enums.ItemType.TSHIRT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ShoppingControllerTests {

    @Autowired
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new CustomerBasket(new ArrayList<>(), STANDARD_CUSTOMER))
        );
    }

    @Test
    void should_return_0_when_empty_shopping_list_for_any_customer() {
        String standardCustomer = controller.getPrice(new CustomerBasket(new ArrayList<>(), STANDARD_CUSTOMER));
        assertEquals(standardCustomer, "0.0");

        String premiumCustomer = controller.getPrice(new CustomerBasket(new ArrayList<>(), PREMIUM_CUSTOMER));
        assertEquals(premiumCustomer, "0.0");

        String platinumCustomer = controller.getPrice(new CustomerBasket(new ArrayList<>(), PLATINUM_CUSTOMER));
        assertEquals(platinumCustomer, "0.0");
    }

    @Test
    void should_return_60_when_2_tshirts_for_standard_customer() {
        Item tShirt = new Item(TSHIRT, 2);
        CustomerBasket body = new CustomerBasket(List.of(tShirt), STANDARD_CUSTOMER);

        String standard_customer = controller.getPrice(body);
        assertEquals(standard_customer, "60.0");
    }

    @Test
    void should_throw_BAD_REQUEST_due_too_price_too_high() {
        Item tShirt = new Item(TSHIRT, 10);
        CustomerBasket body = new CustomerBasket(List.of(tShirt), STANDARD_CUSTOMER);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> controller.getPrice(body));
        assertEquals("400 BAD_REQUEST \"Price (300.0) is too high for STANDARD_CUSTOMER\"", responseStatusException.getMessage());
    }
}
