package com.blackberrypizzas.orders.domain.pizza;

import com.blackberrypizzas.orders.domain.enums.PizzaSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private String flavor;
    private PizzaSize size;
    private Boolean twoFlavors = false;
}
