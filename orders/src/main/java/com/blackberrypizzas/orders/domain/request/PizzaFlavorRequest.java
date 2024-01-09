package com.blackberrypizzas.orders.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaFlavorRequest {
    private String flavor;
    private Double price;
    private String description;
}
