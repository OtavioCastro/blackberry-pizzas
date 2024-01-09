package com.blackberrypizzas.orders.controller;

import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import com.blackberrypizzas.orders.domain.order.Order;
import com.blackberrypizzas.orders.domain.request.CancelOrderRequest;
import com.blackberrypizzas.orders.domain.request.PizzaFlavorRequest;
import com.blackberrypizzas.orders.domain.request.PizzaOrderRequest;
import com.blackberrypizzas.orders.domain.request.StatusOrderUpdateRequest;
import com.blackberrypizzas.orders.service.PizzaService;
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
    public List<Order> getPizzaOrders(@RequestParam("costumerCpf") String costumerCpf){
        log.log(Level.INFO, "Obtendo os pedidos de pizza para o cpf " + costumerCpf);

        return pizzaService.getPizzaOrders(costumerCpf);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/flavor")
    public void createPizzaFlavor(@RequestBody PizzaFlavorRequest pizzaFlavorRequest){
        pizzaService.createPizzaFlavor(pizzaFlavorRequest);
    }
}
