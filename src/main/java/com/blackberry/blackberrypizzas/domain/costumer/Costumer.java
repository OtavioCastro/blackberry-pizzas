package com.blackberry.blackberrypizzas.domain.costumer;

import lombok.Data;

@Data
public class Costumer {
    private String name;
    private String costumerPhone;
    private CostumerAddress costumerAddress;
}
