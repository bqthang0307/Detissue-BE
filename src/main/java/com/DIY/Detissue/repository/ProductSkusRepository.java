package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.ProductSkus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkusRepository extends JpaRepository<ProductSkus,Integer> {
    @Query("SELECT MIN(p.price) FROM ProductSkus p WHERE p.product.id = ?1")
    Long findByProductIdWithMinPrice(int id);
    @Query("SELECT Max(p.price) FROM ProductSkus p WHERE p.product.id = ?1")
    Long findByProductIdWithMaxPrice(int id);
}
