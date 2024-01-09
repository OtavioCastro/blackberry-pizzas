package com.blackberrypizzas.orders.integration.impl;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import com.blackberrypizzas.orders.gateway.CostumerGateway;
import com.blackberrypizzas.orders.integration.CRMIntegration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerGatewayImpl implements CostumerGateway {

    final CRMIntegration crmIntegration;

    @Override
    public Costumer findCostumer(String costumerPhone) {
        return crmIntegration.getCostumer(costumerPhone);
    }

    @Override
    public CostumerAddress findCostumerAddressById(Long id) {
        return crmIntegration.getCostumerAddressById(id);
    }
}
