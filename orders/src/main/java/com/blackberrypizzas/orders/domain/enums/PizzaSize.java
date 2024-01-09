package com.blackberrypizzas.orders.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PizzaSize {
    SMALL(1, 4, false),
    LARGE(2, 8, true);

    private final Integer externalId;
    private final Integer slices;
    private final Boolean canTwoFlavors;

}
