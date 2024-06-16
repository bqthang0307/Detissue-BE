package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query("SELECT s FROM ProductSkus p " +
            "Join p.size s WHERE p.product.id = ?1")
    List<Size> findByProductId(int id);
}
