package com.blackberrypizzas.orders.service.converter;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import com.blackberrypizzas.orders.domain.order.Order;
import com.blackberrypizzas.orders.domain.pizza.Pizza;
import com.blackberrypizzas.orders.gateway.CostumerGateway;
import com.blackberrypizzas.orders.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderEntityToOrderConverter {

    private final ModelMapper modelMapper;
    private final CostumerGateway costumerGateway;
    private final PizzaEntityToPizzaConverter pizzaEntityToPizzaConverter;

    public Order convert(OrderEntity orderEntity){
        CostumerAddress costumerAddress = costumerGateway.findCostumerAddressById(orderEntity.getCostumerAddressId());

        return Order.builder()
                .orderNumber(orderEntity.getOrderNumber())
                .price(orderEntity.getPrice())
                .pizzas(convertPizzasEntity(orderEntity))
                .orderDate(orderEntity.getOrderDate())
                .costumerAddress(costumerAddress)
                .status(orderEntity.getStatus())
                .build();

        //return modelMapper.map(orderEntity, Order.class);
    }

    private List<Pizza> convertPizzasEntity(OrderEntity orderEntity) {
        return orderEntity.getPizzas()
                .stream().map(pizzaEntityToPizzaConverter::convert)
                .collect(Collectors.toList());
    }
}
