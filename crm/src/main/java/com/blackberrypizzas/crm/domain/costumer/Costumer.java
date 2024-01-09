package com.blackberrypizzas.crm.domain.costumer;

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
    private String cpf;
    private String name;
    private String costumerPhone;
    private List<CostumerAddress> costumerAddresses;
}
