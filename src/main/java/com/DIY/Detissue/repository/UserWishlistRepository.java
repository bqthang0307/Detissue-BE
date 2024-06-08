package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.entity.UserWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserWishlistRepository extends JpaRepository<UserWishlist, Integer>{
    @Query("SELECT p from UserWishlist u " +
            "join u.product p WHERE u.user.id = ?1")
    List<Product> findUserWishListByUserId(int id);
    UserWishlist findByUserIdAndProductId(int userId, int productId);
}
