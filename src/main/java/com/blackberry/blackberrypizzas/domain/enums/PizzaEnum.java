package com.blackberry.blackberrypizzas.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PizzaEnum {
    MUSSARELA("MUSSARELA", 39.90),
    PORTUGUESA("PORTUGUESA", 41.90),
    CALABRESA("CALABRESA", 39.90),
    FRANGO_COM_CATUPIRY("FRANGO COM CATUPIRY", 41.90),
    FRANGO("FRANGO", 39.90);

    private final String flavor;
    private final Double price;
}
