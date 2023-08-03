package com.blackberry.blackberrypizzas.service.impl;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;
import com.blackberry.blackberrypizzas.repository.CostumerAddressRepository;
import com.blackberry.blackberrypizzas.repository.CostumerRepository;
import com.blackberry.blackberrypizzas.repository.OrderRepository;
import com.blackberry.blackberrypizzas.repository.PizzaRepository;
import com.blackberry.blackberrypizzas.repository.entity.CostumerAddressEntity;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import com.blackberry.blackberrypizzas.service.PizzaService;
import com.blackberry.blackberrypizzas.service.converter.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {

    private final CostumerRepository costumerRepository;
    private final CostumerAddressRepository costumerAddressRepository;
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;

    private final CostumerEntityToCostumerConverter costumerEntityToCostumerConverter;
    private final CostumerToCostumerEntityConverter costumerToCostumerEntityConverter;
    private final OrderEntityToOrderConverter orderEntityToOrderConverter;
    private final OrderToOrderEntityConverter orderToOrderEntityConverter;
    private final PizzaEntityToPizzaConverter pizzaEntityToPizzaConverter;
    private final PizzaToPizzaEntityConverter pizzaToPizzaEntityConverter;

    @Override
    public List<Order> getPizzaOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderEntityToOrderConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest) {

        CostumerEntity costumerEntity = Optional.ofNullable(costumerRepository.findByCostumerPhone(pizzaOrderRequest.getCostumer().getCostumerPhone()))
                .orElseGet(() -> buildCostumerEntity(pizzaOrderRequest.getCostumer()));

        final OrderEntity orderEntity = OrderEntity.builder()
                .orderNumer(1234L)
                .costumer(costumerEntity)
                .price(200.00)
                .pizzas(buildPizzasEntity(pizzaOrderRequest.getPizza()))
                .status(StatusOrder.TO_DO)
                .orderDate(Date.from(Instant.now()))
                .build();

        OrderEntity order = orderRepository.save(orderEntity);

        return orderEntityToOrderConverter.convert(order);
    }

    private List<PizzaEntity> buildPizzasEntity(List<Pizza> pizza) {
        List<PizzaEntity> pizzaEntityList = pizza.stream()
                .map(pizzaToPizzaEntityConverter::convert).toList();
        return pizzaRepository.saveAll(pizzaEntityList);
    }

    private CostumerEntity buildCostumerEntity(Costumer costumer) {
        CostumerEntity costumerEntity = costumerToCostumerEntityConverter.convert(costumer);
        //CostumerAddressEntity costumerAddressEntity = costumerEntity.getCostumerAddress();
        //costumerAddressRepository.save(costumerAddressEntity);
        return costumerRepository.save(costumerEntity);
    }

}
