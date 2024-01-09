package com.blackberrypizzas.orders.domain.request;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.pizza.Pizza;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaOrderRequest {
    private List<Pizza> pizzas;
    private String costumerCpf;
    private Long costumerAddressId;
    private String costumerPhone;
}
