package com.blackberry.blackberrypizzas.domain.costumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Costumer {
    private String name;
    private String costumerPhone;
    private CostumerAddress costumerAddress;
}
