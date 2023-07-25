package com.blackberry.blackberrypizzas.domain.order;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import lombok.Data;

@Data
public class PizzaOrder {
    private Double orderNumer;
    private Costumer costumer;
    private Double price;
    private Pizza pizza;
}
