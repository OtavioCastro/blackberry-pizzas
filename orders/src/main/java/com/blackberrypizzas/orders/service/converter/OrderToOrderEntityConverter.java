package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.order.Order;
import com.blackberrypizzas.orders.repository.entity.OrderEntity;
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
