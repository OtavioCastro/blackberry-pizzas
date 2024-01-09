package com.blackberrypizzas.crm.controller.dto;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;

import java.util.List;

public record CostumerRecordDto(
        String costumerPhone,
        String costumerCpf,
        String costumerName,
        List<CostumerAddress> costumerAddresses
) {
}
