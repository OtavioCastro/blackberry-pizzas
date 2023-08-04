package com.blackberry.blackberrypizzas.domain.pizza;

import com.blackberry.blackberrypizzas.domain.enums.PizzaSize;
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
}
