package com.blackberrypizzas.orders.repository;

import com.blackberrypizzas.orders.repository.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
}
