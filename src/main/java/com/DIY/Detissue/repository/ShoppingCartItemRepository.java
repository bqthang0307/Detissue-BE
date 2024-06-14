package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer>{
    @Query("SELECT s FROM ShoppingCartItem s " +
            "join s.cart c WHERE s.cart.user.id = ?1")
    List<ShoppingCartItem> findByUserId(int id);

    @Query("SELECT s FROM ShoppingCartItem s " +
            "join s.cart c WHERE s.cart.user.id = ?1 AND s.productSkus.product.id = ?2 AND s.productSkus.size.id = ?3")
    ShoppingCartItem findByUserIdAndProductIdAndSizeId(int userId, int productId, int sizeId);
}
