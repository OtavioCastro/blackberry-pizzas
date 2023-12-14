package com.blackberry.blackberrypizzas.service.impl;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.pizza.Pizza;
import com.blackberry.blackberrypizzas.domain.request.PizzaFlavorRequest;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;
import com.blackberry.blackberrypizzas.repository.CostumerRepository;
import com.blackberry.blackberrypizzas.repository.OrderRepository;
import com.blackberry.blackberrypizzas.repository.PizzaFlavorRepository;
import com.blackberry.blackberrypizzas.repository.PizzaRepository;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import com.blackberry.blackberrypizzas.repository.entity.PizzaFlavorEntity;
import com.blackberry.blackberrypizzas.service.CostumerService;
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
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Log4j2
@AllArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {

    private final CostumerService costumerService;

    private final CostumerRepository costumerRepository;
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final PizzaFlavorRepository pizzaFlavorRepository;

    private final CostumerEntityToCostumerConverter costumerEntityToCostumerConverter;
    private final CostumerToCostumerEntityConverter costumerToCostumerEntityConverter;
    private final OrderEntityToOrderConverter orderEntityToOrderConverter;
    private final OrderToOrderEntityConverter orderToOrderEntityConverter;
    private final PizzaEntityToPizzaConverter pizzaEntityToPizzaConverter;
    private final PizzaToPizzaEntityConverter pizzaToPizzaEntityConverter;

    private static Integer ORDER_QUANTITY = 0;
    private static final List<StatusOrder> STATUS_ALLOW_CANCEL = List.of(StatusOrder.TO_DO, StatusOrder.IN_PROGRESS);
    private static final List<StatusOrder> STATUS_NOT_ALLOW_CHANGE = List.of(StatusOrder.CANCELED, StatusOrder.DELIVERED);

    @Override
    public List<Order> getPizzaOrders(String costumerPhone) {
        return orderRepository.findByCostumerPhone(costumerPhone)
                .stream()
                .map(orderEntityToOrderConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest) {

        log.log(Level.INFO, "Carregando dados do Cliente...");

        CostumerEntity costumerEntity = ofNullable(costumerRepository.findByCostumerPhone(
                pizzaOrderRequest.getCostumer().getCostumerPhone())
                )
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cliente não cadastrado!"));

        final OrderEntity orderEntity = OrderEntity.builder()
                .orderNumber(generateOrderNumber())
                .costumer(costumerEntity)
                .price(calculateOrderPrice(pizzaOrderRequest.getPizzas()))
                .pizzas(buildPizzasEntity(pizzaOrderRequest.getPizzas()))
                .status(StatusOrder.TO_DO)
                .orderDate(Date.from(Instant.now()))
                .build();

        OrderEntity order = orderRepository.save(orderEntity);

        return orderEntityToOrderConverter.convert(order);
    }

    @Override
    public void updateOrder(String orderNumber, StatusOrder statusOrder) {
        OrderEntity order = orderRepository.findByOrderNumber(Long.valueOf(orderNumber));

        if(statusOrder.equals(StatusOrder.CANCELED)){
            log.log(Level.INFO, "Realizando o cancelamento do pedido " + orderNumber);
            cancelOrder(order);
        } else {
            log.log(Level.INFO, "Atualizando o status do pedido " + orderNumber);
            changeOrder(order, statusOrder);
        }
    }

    @Override
    public void createPizzaFlavor(PizzaFlavorRequest pizzaFlavorRequest) {
        if(isNull(pizzaFlavorRepository.findByFlavor(pizzaFlavorRequest.getFlavor()))){
            final PizzaFlavorEntity pizzaFlavorToSave = PizzaFlavorEntity.builder()
                    .flavor(pizzaFlavorRequest.getFlavor())
                    .price(pizzaFlavorRequest.getPrice())
                    .description(pizzaFlavorRequest.getDescription())
                    .build();
            pizzaFlavorRepository.save(pizzaFlavorToSave);
        } else throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Sabor de pizza já cadastrado no sistema");
    }

    private void cancelOrder(OrderEntity order) {
        if(STATUS_ALLOW_CANCEL.contains(order.getStatus())){
            orderRepository.changeOrderStatus(StatusOrder.CANCELED, order.getOrderNumber());
        } else {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não é possível cancelar o pedido, pois ele está com o status " + order.getStatus().getStatus());
        }
    }

    private void changeOrder(OrderEntity order, StatusOrder statusOrder) {
        if(!STATUS_NOT_ALLOW_CHANGE.contains(order.getStatus())){
            orderRepository.changeOrderStatus(statusOrder, order.getOrderNumber());
        } else {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não é possível alterar o status do pedido, pois ele está com o status " + order.getStatus().getStatus());
        }
    }

    private Long generateOrderNumber() {
        log.log(Level.INFO, "Gerando o número do pedido");

        ORDER_QUANTITY++;
        String orderCode = new SimpleDateFormat("yyMMdd").format(Date.from(Instant.now())).concat(ORDER_QUANTITY.toString());

        log.log(Level.INFO, "Número do pedido gerado! Número: " + orderCode);

        return Long.valueOf(orderCode);
    }

    private Double calculateOrderPrice(List<Pizza> pizzas) {
        return pizzas.stream()
                .map(this::getPizzaFlavor)
                .map(pizzaFlavorRepository::findByFlavor)
                .map(PizzaFlavorEntity::getPrice)
                .reduce(0.00, Double::sum);
    }

    private String getPizzaFlavor(Pizza pizza) {
        if(pizza.getTwoFlavors()){
            return calculateMostExpensiveFlavor(pizza.getFlavor());
        } else {
            return getFlavorRepository(pizza.getFlavor()).getFlavor();
        }
    }

    private PizzaFlavorEntity getFlavorRepository(String flavor) {
        return ofNullable(pizzaFlavorRepository.findByFlavor(flavor))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Sabor de pizza não encontrado."));
    }

    private String calculateMostExpensiveFlavor(String flavor) {
        final List<String> flavors = Arrays.stream(flavor.split("/")).toList();
        return flavors.stream()
                .map(this::getFlavorRepository)
                .max(comparingDouble(PizzaFlavorEntity::getPrice))
                .map(PizzaFlavorEntity::getFlavor)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Sabor de pizza não encontrado."));
    }

    private List<PizzaEntity> buildPizzasEntity(List<Pizza> pizza) {
        List<PizzaEntity> pizzaEntityList = pizza.stream()
                .map(pizzaToPizzaEntityConverter::convert).toList();
        return pizzaRepository.saveAll(pizzaEntityList);
    }

}
