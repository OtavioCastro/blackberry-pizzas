package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.costumer.CostumerAddress;
import com.blackberry.blackberrypizzas.repository.entity.CostumerAddressEntity;
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
