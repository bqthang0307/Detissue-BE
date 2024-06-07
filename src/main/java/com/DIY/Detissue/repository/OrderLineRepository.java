package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByShopOrderId(int id);
}
