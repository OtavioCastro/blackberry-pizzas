package com.blackberrypizzas.orders.gateway;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;

public interface CostumerGateway {
    Costumer findCostumer(String costumerPhone);
    CostumerAddress findCostumerAddressById(Long id);
}
