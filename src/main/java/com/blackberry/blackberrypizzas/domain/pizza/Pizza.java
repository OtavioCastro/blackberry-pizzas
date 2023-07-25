package com.blackberry.blackberrypizzas.domain.pizza;

import lombok.Data;

@Data
public class Pizza {
    private String flavor;
    private PizzaSize size;
}
