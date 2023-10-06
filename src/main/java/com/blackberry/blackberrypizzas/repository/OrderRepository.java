package com.blackberry.blackberrypizzas.repository;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import com.blackberry.blackberrypizzas.repository.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT o FROM ORDER_PIZZA o INNER JOIN o.costumer c WHERE c.costumerPhone = :costumerPhoneRequested ORDER BY o.id DESC")
    List<OrderEntity> findByCostumerPhone(@Param("costumerPhoneRequested") String costumerPhone);

    OrderEntity findByOrderNumber(Long orderNumber);

    @Modifying
    @Transactional
    @Query("UPDATE ORDER_PIZZA o SET o.status = :newStatus WHERE o.orderNumber = :orderNumberToChange")
    void changeOrderStatus(@Param("newStatus") StatusOrder statusOrder, @Param("orderNumberToChange") Long orderNumber);
}
