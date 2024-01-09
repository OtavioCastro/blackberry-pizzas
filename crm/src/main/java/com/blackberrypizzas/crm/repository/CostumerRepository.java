package com.blackberrypizzas.crm.repository;


import com.blackberrypizzas.crm.repository.entity.CostumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<CostumerEntity, Long> {
    CostumerEntity findByCostumerCpf(String cpf);
}
