package com.blackberry.blackberrypizzas.service.converter;

import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEntityToOrderConverter {

    private final ModelMapper modelMapper;

    public Order convert(OrderEntity orderEntity){
        return modelMapper.map(orderEntity, Order.class);
    }
}
