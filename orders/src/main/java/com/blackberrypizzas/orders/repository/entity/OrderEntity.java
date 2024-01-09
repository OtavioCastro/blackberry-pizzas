package com.blackberrypizzas.orders.repository.entity;

import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDER_PIZZA")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderNumber;
    private String costumerCpf;
    private Long costumerAddressId;
    private Double price;
    @OneToMany
    private List<PizzaEntity> pizzas;
    @Enumerated(EnumType.ORDINAL)
    private StatusOrder status;
    private Date orderDate;
}

