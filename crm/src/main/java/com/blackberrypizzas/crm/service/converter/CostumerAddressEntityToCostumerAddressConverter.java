package com.blackberrypizzas.crm.service.converter;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import com.blackberrypizzas.crm.repository.entity.CostumerAddressEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerAddressEntityToCostumerAddressConverter {

    private final ModelMapper modelMapper;

    public CostumerAddress convert(CostumerAddressEntity costumerAddressEntity){
        return modelMapper.map(costumerAddressEntity, CostumerAddress.class);
    }
}
