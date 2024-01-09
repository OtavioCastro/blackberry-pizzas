package com.blackberrypizzas.crm.service.converter;

import com.blackberrypizzas.crm.domain.costumer.Costumer;
import com.blackberrypizzas.crm.repository.entity.CostumerEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerEntityToCostumerConverter {

    private final ModelMapper modelMapper;

    public Costumer convert(CostumerEntity costumerEntity){
        return modelMapper.map(costumerEntity, Costumer.class);
    }
}
