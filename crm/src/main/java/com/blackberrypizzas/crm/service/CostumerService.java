package com.blackberrypizzas.crm.service;


import com.blackberrypizzas.crm.domain.costumer.Costumer;
import com.blackberrypizzas.crm.domain.request.CostumerAddDto;
import com.blackberrypizzas.crm.domain.request.CostumerAddresAddDto;
import com.blackberrypizzas.crm.domain.request.UpdateCostumerDto;

public interface CostumerService {
    Costumer findCostumer(String costumerPhone);
    Costumer addCostumer(CostumerAddDto costumer);
    void updateCostumer(UpdateCostumerDto updateCostumerDto);
    void saveCostumerAddress(CostumerAddresAddDto costumerAddressAddDto);
}
