package com.blackberrypizzas.orders.repository;

import com.blackberrypizzas.orders.repository.entity.PizzaFlavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaFlavorRepository extends JpaRepository<PizzaFlavorEntity, Long> {
    PizzaFlavorEntity findByFlavor(String flavor);
}
