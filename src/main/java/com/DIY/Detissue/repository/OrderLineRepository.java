package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByShopOrderId(int id);

    @Query("SELECT ol FROM OrderLine ol WHERE ol.shopOrder.user.id = ?1 AND ol.productSkus.product.id = ?2")
    List<OrderLine> findByUserIdAndProductId(int userId, int productId);
}
