package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    @Query("SELECT s FROM OrderStatus s")
    List<OrderStatus> findAll();
}
