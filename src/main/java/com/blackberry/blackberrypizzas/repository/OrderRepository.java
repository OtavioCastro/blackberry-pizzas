package com.blackberry.blackberrypizzas.repository;

import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
