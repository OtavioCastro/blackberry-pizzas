package com.blackberry.blackberrypizzas.domain.order;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
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
    private Long orderNumber;
    private Costumer costumer;
    private Double price;
    private List<Pizza> pizzas;
    private StatusOrder status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;
}
