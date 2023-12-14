package com.blackberry.blackberrypizzas.repository;

import com.blackberry.blackberrypizzas.repository.entity.PizzaFlavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaFlavorRepository extends JpaRepository<PizzaFlavorEntity, Long> {
    PizzaFlavorEntity findByFlavor(String flavor);
}
