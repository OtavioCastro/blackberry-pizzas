package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
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
