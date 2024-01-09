package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.repository.entity.CostumerEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerToCostumerEntityConverter {


    private final ModelMapper modelMapper;

    public CostumerEntity convert(Costumer costumer){
        return modelMapper.map(costumer, CostumerEntity.class);
    }
}
