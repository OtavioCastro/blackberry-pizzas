package com.blackberrypizzas.crm.domain.request;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostumerAddDto {
    private String cpf;
    private String costumerName;
    private String costumerPhone;
    private CostumerAddress costumerAddress;
}