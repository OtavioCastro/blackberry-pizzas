package com.blackberry.blackberrypizzas.controller;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.domain.order.Order;
import com.blackberry.blackberrypizzas.domain.request.CancelOrderRequest;
import com.blackberry.blackberrypizzas.domain.request.PizzaOrderRequest;
import com.blackberry.blackberrypizzas.domain.request.StatusOrderUpdateRequest;
import com.blackberry.blackberrypizzas.service.PizzaService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@Log4j2
@AllArgsConstructor
public class PizzaController {

    private PizzaService pizzaService;

    @GetMapping
    public List<Order> getPizzaOrders(@RequestParam("costumerPhone") String costumerPhone){
        log.log(Level.INFO, "Obtendo os pedidos de pizza para o número de cadastro " + costumerPhone);

        return pizzaService.getPizzaOrders(costumerPhone);
    }

    @PostMapping
    public Order sendPizzaOrder(@RequestBody PizzaOrderRequest pizzaOrderRequest){
        log.log(Level.INFO, "Gerando o pedido de Pizza...");

        return pizzaService.sendPizzaOrder(pizzaOrderRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/cancel")
    public void cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest){
        pizzaService.updateOrder(cancelOrderRequest.getOrderNumber(), StatusOrder.CANCELED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update")
    public void updateStatusOrder(@RequestBody StatusOrderUpdateRequest statusOrderUpdateRequest){
        pizzaService.updateOrder(statusOrderUpdateRequest.getOrderNumber(), statusOrderUpdateRequest.getStatusOrder());
    }
}
