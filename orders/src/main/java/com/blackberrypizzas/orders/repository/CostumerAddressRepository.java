package com.blackberrypizzas.orders.repository;

import com.blackberrypizzas.orders.repository.entity.CostumerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerAddressRepository extends JpaRepository<CostumerAddressEntity, Long> {
}
