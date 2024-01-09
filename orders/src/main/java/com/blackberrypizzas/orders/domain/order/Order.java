package com.blackberrypizzas.orders.domain.order;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import com.blackberrypizzas.orders.domain.pizza.Pizza;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long orderNumber;
    private Costumer costumer;
    private CostumerAddress costumerAddress;
    private Double price;
    private List<Pizza> pizzas;
    private StatusOrder status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
}
