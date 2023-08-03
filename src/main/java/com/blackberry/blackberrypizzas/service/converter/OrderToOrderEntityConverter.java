package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderToOrderEntityConverter {

    private final ModelMapper modelMapper;

    public OrderEntity convert(Order order){
        return modelMapper.map(order, OrderEntity.class);
    }
}
