package com.blackberrypizzas.crm.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CostumerAddresAddDto {
    private String street;
    private String postalCode;
    private String neighborhood;
    private String city;
    private String costumerCpf;
}
