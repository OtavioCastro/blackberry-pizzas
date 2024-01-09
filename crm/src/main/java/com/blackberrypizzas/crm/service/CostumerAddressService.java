package com.blackberrypizzas.crm.service;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import com.blackberrypizzas.crm.domain.request.CostumerAddresAddDto;

import java.util.List;

public interface CostumerAddressService {
    List<CostumerAddress> getCostumerAddressess(String costumerCpf);
    CostumerAddress getCostumerAddressById(Long addressId);
    void saveCostumerAddress(CostumerAddresAddDto costumerAddressAddDto);
}
