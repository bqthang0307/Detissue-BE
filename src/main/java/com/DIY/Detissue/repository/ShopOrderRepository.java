package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder,Integer>{
    List<ShopOrder> findByUserId(int id);
}
