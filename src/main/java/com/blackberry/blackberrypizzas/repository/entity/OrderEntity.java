package com.blackberry.blackberrypizzas.repository.entity;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
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
    @ManyToOne
    private CostumerEntity costumer;
    private Double price;
    @OneToMany
    private List<PizzaEntity> pizzas;
    @Enumerated(EnumType.ORDINAL)
    private StatusOrder status;
    private Date orderDate;
}

