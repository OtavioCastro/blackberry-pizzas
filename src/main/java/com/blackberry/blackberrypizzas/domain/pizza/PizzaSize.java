package com.blackberry.blackberrypizzas.domain.pizza;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PizzaSize {
    SMALL(4, false),
    LARGE(8, true);

    private final Integer slices;
    private final Boolean canTwoFlavors;
}
