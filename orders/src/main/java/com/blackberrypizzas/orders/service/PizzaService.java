package com.blackberrypizzas.orders.service;

import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import com.blackberrypizzas.orders.domain.order.Order;
import com.blackberrypizzas.orders.domain.request.PizzaFlavorRequest;
import com.blackberrypizzas.orders.domain.request.PizzaOrderRequest;

import java.util.List;

public interface PizzaService {
    List<Order> getPizzaOrders(String costumerPhone);
    Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest);
    void updateOrder(String orderNumber, StatusOrder statusOrder);
    void createPizzaFlavor(PizzaFlavorRequest pizzaFlavorRequest);
}
