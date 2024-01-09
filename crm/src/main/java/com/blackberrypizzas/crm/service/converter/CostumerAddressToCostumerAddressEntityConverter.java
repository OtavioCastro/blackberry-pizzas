package com.blackberrypizzas.crm.service.converter;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import com.blackberrypizzas.crm.repository.entity.CostumerAddressEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerAddressToCostumerAddressEntityConverter {

    private final ModelMapper modelMapper;

    public CostumerAddressEntity convert(CostumerAddress costumerAddress){
        return modelMapper.map(costumerAddress, CostumerAddressEntity.class);
    }
}
