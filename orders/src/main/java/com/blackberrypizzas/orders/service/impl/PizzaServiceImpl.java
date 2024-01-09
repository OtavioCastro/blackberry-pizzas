package com.blackberrypizzas.orders.service.impl;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import com.blackberrypizzas.orders.domain.order.Order;
import com.blackberrypizzas.orders.domain.pizza.Pizza;
import com.blackberrypizzas.orders.domain.request.PizzaFlavorRequest;
import com.blackberrypizzas.orders.domain.request.PizzaOrderRequest;
import com.blackberrypizzas.orders.gateway.CostumerGateway;
import com.blackberrypizzas.orders.repository.CostumerRepository;
import com.blackberrypizzas.orders.repository.OrderRepository;
import com.blackberrypizzas.orders.repository.PizzaFlavorRepository;
import com.blackberrypizzas.orders.repository.PizzaRepository;
import com.blackberrypizzas.orders.repository.entity.OrderEntity;
import com.blackberrypizzas.orders.repository.entity.PizzaEntity;
import com.blackberrypizzas.orders.repository.entity.PizzaFlavorEntity;
import com.blackberrypizzas.orders.service.PizzaService;
import com.blackberrypizzas.orders.service.converter.*;
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
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Log4j2
@AllArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {

    private final CostumerGateway costumerGateway;
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
    public List<Order> getPizzaOrders(String costumerCpf) {
        Costumer costumer = costumerGateway.findCostumer(costumerCpf);
        return orderRepository.findByCostumerCpf(costumerCpf)
                .stream()
                .map(orderEntityToOrderConverter::convert)
                .map(order -> order.withCostumer(costumer))
                .collect(Collectors.toList());
    }

    @Override
    public Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest) {

        log.log(Level.INFO, "Carregando dados do Cliente...");

        Costumer costumer = costumerGateway.findCostumer(pizzaOrderRequest.getCostumerCpf());
        CostumerAddress costumerAddress = costumerGateway.findCostumerAddressById(pizzaOrderRequest.getCostumerAddressId());

        OrderEntity orderEntity = saveOrderEntity(pizzaOrderRequest);

        return Order.builder()
                .orderNumber(orderEntity.getOrderNumber())
                .price(orderEntity.getPrice())
                .pizzas(convertPizzasEntity(orderEntity))
                .orderDate(orderEntity.getOrderDate())
                .costumer(costumer)
                .costumerAddress(costumerAddress)
                .status(orderEntity.getStatus())
                .build();
    }

    private List<Pizza> convertPizzasEntity(OrderEntity orderEntity) {
        return orderEntity.getPizzas()
                .stream().map(pizzaEntityToPizzaConverter::convert)
                .collect(Collectors.toList());
    }

    private OrderEntity saveOrderEntity(PizzaOrderRequest pizzaOrderRequest) {
        final OrderEntity orderEntity = OrderEntity.builder()
                .orderNumber(generateOrderNumber())
                .costumerCpf(pizzaOrderRequest.getCostumerCpf())
                .costumerAddressId(pizzaOrderRequest.getCostumerAddressId())
                .price(calculateOrderPrice(pizzaOrderRequest.getPizzas()))
                .pizzas(buildPizzasEntity(pizzaOrderRequest.getPizzas()))
                .status(StatusOrder.TO_DO)
                .orderDate(Date.from(Instant.now()))
                .build();

        return orderRepository.save(orderEntity);
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
