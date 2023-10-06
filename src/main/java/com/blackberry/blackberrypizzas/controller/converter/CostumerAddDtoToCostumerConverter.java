package com.blackberry.blackberrypizzas.controller.converter;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.request.CostumerAddDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CostumerAddDtoToCostumerConverter {

    private final ModelMapper modelMapper;

    public Costumer convert(CostumerAddDto costumerAddDto){
        return modelMapper.map(costumerAddDto, Costumer.class);
    }
}
