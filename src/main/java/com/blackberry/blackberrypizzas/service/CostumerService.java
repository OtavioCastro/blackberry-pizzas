package com.blackberry.blackberrypizzas.service;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.request.UpdateCostumerDto;

public interface CostumerService {
    Costumer addCostumer(Costumer costumer);
    void updateCostumer(UpdateCostumerDto updateCostumerDto);
}
