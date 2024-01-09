package com.blackberrypizzas.crm.controller.converter;

import com.blackberrypizzas.crm.domain.costumer.Costumer;
import com.blackberrypizzas.crm.domain.request.CostumerAddDto;
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
