package com.blackberry.blackberrypizzas.domain.order;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderNumer;
    private Costumer costumer;
    private Double price;
    private List<Pizza> pizzas;
    private StatusOrder status;
    private Date orderDate;
}
