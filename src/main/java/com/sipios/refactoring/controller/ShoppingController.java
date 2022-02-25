package com.sipios.refactoring.controller;

import com.sipios.refactoring.model.CustomerBasket;
import com.sipios.refactoring.service.ShoppingService;
import com.sipios.refactoring.service.exceptions.PriceTooHighException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    private final Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public String getPrice(@RequestBody CustomerBasket customerBasket) {
        try {
            return String.valueOf(shoppingService.computePrice(customerBasket));
        } catch (PriceTooHighException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

