package com.blackberrypizzas.crm.repository;

import com.blackberrypizzas.crm.repository.entity.CostumerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CostumerAddressRepository extends JpaRepository<CostumerAddressEntity, Long> {
/*    @Query("select ca CostumerAddressEntity inner join ca.costumer Costumer where c.costumerCpf =: id")
    List<CostumerAddressEntity> findByCostumerCpf(String id);*/
}
