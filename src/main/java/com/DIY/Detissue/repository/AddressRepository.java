package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Address;
import com.DIY.Detissue.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select u.address from UserAddress u where u.user.id = ?1")
    List<Address> findByUserId(int userId);

    @Query("select u.address from UserAddress u where u.user.id = ?1 and u.isDefault = true")
    Address findDefaultAddressByUserId(int userId);
}
