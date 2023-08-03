package com.blackberry.blackberrypizzas.service;

import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;

import java.util.List;

public interface PizzaService {
    List<Order> getPizzaOrders();
    Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest);
}
