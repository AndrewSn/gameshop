package com.gameshop.repository;

import com.gameshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("SELECT o from Order o where o.createOrder BETWEEN :startDAte and :endDAte")
    List<Order> getOrderByDateRange(@Param("startDAte") LocalDate startDAte, @Param("endDAte") LocalDate endDAte);

    @Query("SELECT o from Order o where  o.userInfo.userInfoId = :id")
    List<Order> getOrderByUserId(@Param("id") Long id);
}
