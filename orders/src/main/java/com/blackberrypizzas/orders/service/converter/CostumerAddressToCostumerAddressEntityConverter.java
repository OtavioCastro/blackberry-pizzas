package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import com.blackberrypizzas.orders.repository.entity.CostumerAddressEntity;
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
