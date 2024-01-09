package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.pizza.Pizza;
import com.blackberrypizzas.orders.repository.entity.PizzaEntity;
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
