package com.blackberry.blackberrypizzas.domain.costumer;

import lombok.Data;

@Data
public class CostumerAddress {
    private String street;
    private String postalCode;
    private String neighborhood;
    private String city;
}
