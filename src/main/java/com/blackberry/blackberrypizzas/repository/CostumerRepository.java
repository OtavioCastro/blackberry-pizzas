package com.blackberry.blackberrypizzas.repository;

import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<CostumerEntity, Long> {
    CostumerEntity findByCostumerPhone(String costumerPhone);
}
