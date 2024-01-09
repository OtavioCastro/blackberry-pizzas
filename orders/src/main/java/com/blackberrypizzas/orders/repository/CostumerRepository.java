package com.blackberrypizzas.orders.repository;

import com.blackberrypizzas.orders.repository.entity.CostumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<CostumerEntity, Long> {
    CostumerEntity findByCostumerPhone(String costumerPhone);
    CostumerEntity findByCostumerCpf(String cpf);
}
