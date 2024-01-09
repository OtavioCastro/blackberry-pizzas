package com.blackberrypizzas.orders.domain.costumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Costumer {
    //private Long id;
    private String costumerName;
    private String costumerCpf;
    private String costumerPhone;
    private List<CostumerAddress> costumerAddresses;
}
