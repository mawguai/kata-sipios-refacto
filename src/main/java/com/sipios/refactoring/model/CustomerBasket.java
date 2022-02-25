package com.sipios.refactoring.model;

import com.sipios.refactoring.model.enums.CustomerType;

import java.util.List;

public record CustomerBasket(List<Item> items, CustomerType type) { }
