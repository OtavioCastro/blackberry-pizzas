package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.repository.entity.CostumerEntity;
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
