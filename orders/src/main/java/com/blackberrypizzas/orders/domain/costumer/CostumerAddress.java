package com.blackberrypizzas.orders.domain.costumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostumerAddress {
    private String street;
    private String postalCode;
    private String neighborhood;
    private String city;
}
