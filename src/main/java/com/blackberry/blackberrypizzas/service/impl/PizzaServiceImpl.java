package com.blackberry.blackberrypizzas.service.impl;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.enums.PizzaEnum;
import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;
import com.blackberry.blackberrypizzas.repository.CostumerAddressRepository;
import com.blackberry.blackberrypizzas.repository.CostumerRepository;
import com.blackberry.blackberrypizzas.repository.OrderRepository;
import com.blackberry.blackberrypizzas.repository.PizzaRepository;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import com.blackberry.blackberrypizzas.service.PizzaService;
import com.blackberry.blackberrypizzas.service.converter.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

@Log4j2
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

    private static Integer ORDER_QUANTITY = 0;

    @Override
    public List<Order> getPizzaOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderEntityToOrderConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest) {

        log.log(Level.INFO, "Carregando dados do Cliente...");

        CostumerEntity costumerEntity = Optional.ofNullable(costumerRepository.findByCostumerPhone(pizzaOrderRequest.getCostumer().getCostumerPhone()))
                .orElseGet(() -> buildCostumerEntity(pizzaOrderRequest.getCostumer()));

        final OrderEntity orderEntity = OrderEntity.builder()
                .orderNumer(generateOrderNumber())
                .costumer(costumerEntity)
                .price(calculateOrderPrice(pizzaOrderRequest.getPizzas()))
                .pizzas(buildPizzasEntity(pizzaOrderRequest.getPizzas()))
                .status(StatusOrder.TO_DO)
                .orderDate(Date.from(Instant.now()))
                .build();

        OrderEntity order = orderRepository.save(orderEntity);

        return orderEntityToOrderConverter.convert(order);
    }

    private Long generateOrderNumber() {
        ORDER_QUANTITY++;
        String orderCode = new SimpleDateFormat("yyMMdd").format(Date.from(Instant.now())).concat(ORDER_QUANTITY.toString());
        return Long.valueOf(orderCode);
    }

    private Double calculateOrderPrice(List<Pizza> pizzas) {
        return pizzas.stream()
                .map(pizza -> {
                    if(pizza.getTwoFlavors()){
                        return calculateMostExpensiveFlavor(pizza.getFlavor().replace(" ", "_"));
                    } else {
                        return pizza.getFlavor().replace(" ", "_");
                    }
                })
                .map(pizzaFlavor -> PizzaEnum.valueOf(pizzaFlavor.toUpperCase()))
                .map(PizzaEnum::getPrice)
                .reduce(0.00, Double::sum);
    }

    private String calculateMostExpensiveFlavor(String flavor) {
        final List<String> flavors = Arrays.stream(flavor.split("/")).toList();
        return flavors.stream()
                .map(pizza -> PizzaEnum.valueOf(pizza.toUpperCase()))
                .max(comparingDouble(PizzaEnum::getPrice))
                .map(PizzaEnum::getFlavor)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Sabor de pizza não encontrado."));
    }

    private List<PizzaEntity> buildPizzasEntity(List<Pizza> pizza) {
        List<PizzaEntity> pizzaEntityList = pizza.stream()
                .map(pizzaToPizzaEntityConverter::convert).toList();
        return pizzaRepository.saveAll(pizzaEntityList);
    }

    private CostumerEntity buildCostumerEntity(Costumer costumer) {
        log.log(Level.INFO, "Cliente não cadastrado! Realizando o cadastro...");

        CostumerEntity costumerEntity = costumerToCostumerEntityConverter.convert(costumer);
        return costumerRepository.save(costumerEntity);
    }

}
