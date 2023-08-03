package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PizzaEntityToPizzaConverter {

    private final ModelMapper modelMapper;

    Pizza convert(PizzaEntity pizzaEntity){
        return modelMapper.map(pizzaEntity, Pizza.class);
    }
}
