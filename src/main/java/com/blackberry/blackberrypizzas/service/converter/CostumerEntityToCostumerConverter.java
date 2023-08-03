package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
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
