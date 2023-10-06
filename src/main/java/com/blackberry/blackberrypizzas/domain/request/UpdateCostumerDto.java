package com.blackberry.blackberrypizzas.domain.request;

import com.blackberry.blackberrypizzas.domain.costumer.CostumerAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCostumerDto {
    private String cpf;
    private String costumerPhone;
    private CostumerAddress costumerAddress;
}
