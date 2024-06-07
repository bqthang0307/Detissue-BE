package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
    @Query(value = "select * from user_address where user_id = ?1 and address_id = ?2", nativeQuery = true)
    UserAddress findByUserIdAndAddressId(int userId, int addressId);
}
