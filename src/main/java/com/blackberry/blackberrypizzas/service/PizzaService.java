package com.blackberry.blackberrypizzas.service;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;

import java.util.List;

public interface PizzaService {
    List<Order> getPizzaOrders(String costumerPhone);
    Order sendPizzaOrder(PizzaOrderRequest pizzaOrderRequest);
    void updateOrder(String orderNumber, StatusOrder statusOrder);
}
