package com.blackberry.blackberrypizzas.repository;

import com.blackberry.blackberrypizzas.repository.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
}
