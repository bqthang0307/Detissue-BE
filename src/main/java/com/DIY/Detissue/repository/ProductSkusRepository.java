package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.ProductSkus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSkusRepository extends JpaRepository<ProductSkus,Integer> {
    @Query("SELECT MIN(p.price) FROM ProductSkus p WHERE p.product.id = ?1")
    Long findByProductIdWithMinPrice(int id);
    @Query("SELECT Max(p.price) FROM ProductSkus p WHERE p.product.id = ?1")
    Long findByProductIdWithMaxPrice(int id);
    Optional<ProductSkus> findById(int id);

    List<ProductSkus> findByProductId(int id);
    @Query("SELECT s FROM AttributeOptionSkus a " +
            "join a.skus s " +
            "join a.attributeOption ao WHERE s.product.id = ?1 AND ao.id = ?2")
    Optional<ProductSkus> findByProductIdAndAttributeOptions(int productId, int attributeOptionsId);
}
