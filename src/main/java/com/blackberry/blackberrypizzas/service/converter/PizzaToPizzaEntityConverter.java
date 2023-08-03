package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PizzaToPizzaEntityConverter {

    private final ModelMapper modelMapper;

    public PizzaEntity convert(Pizza pizza){
        return modelMapper.map(pizza, PizzaEntity.class);
    }
}
