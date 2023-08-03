package com.blackberry.blackberrypizzas.controller;

import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.service.PizzaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@Log4j2
@AllArgsConstructor
public class PizzaController {

    private PizzaService pizzaService;

    @GetMapping
    public List<Order> getPizzaOrders(){
        log.log(Level.INFO, "Getting Pizza Orders");

        return pizzaService.getPizzaOrders();
    }

    @PostMapping
    public Order sendPizzaOrder(@RequestBody PizzaOrderRequest pizzaOrderRequest){
        log.log(Level.INFO, "Creating Pizza Order");

        return pizzaService.sendPizzaOrder(pizzaOrderRequest);
    }
}
