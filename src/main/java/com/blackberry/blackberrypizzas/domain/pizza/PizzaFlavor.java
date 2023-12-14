package com.blackberry.blackberrypizzas.domain.pizza;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaFlavor {
    private String flavor;
    private Double price;
    private String description;
}
