package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, PagingAndSortingRepository<Product,Integer> {
    @Query("SELECT p FROM Product p")
    List<Product> findAllProductByPaging(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    List<Product> findByCategoryId(int id, Pageable pageable);
    Product findProductById(int id);

    List<Product> findByNameContaining(String name, Pageable pageable);
}
