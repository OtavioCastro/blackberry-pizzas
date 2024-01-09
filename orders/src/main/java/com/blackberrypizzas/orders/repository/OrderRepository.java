package com.blackberrypizzas.orders.repository;

import com.blackberrypizzas.orders.domain.enums.StatusOrder;
import com.blackberrypizzas.orders.repository.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCostumerCpf(String costumerCpf);

    OrderEntity findByOrderNumber(Long orderNumber);

    @Modifying
    @Transactional
    @Query("UPDATE ORDER_PIZZA o SET o.status = :newStatus WHERE o.orderNumber = :orderNumberToChange")
    void changeOrderStatus(@Param("newStatus") StatusOrder statusOrder, @Param("orderNumberToChange") Long orderNumber);
}
