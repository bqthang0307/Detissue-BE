package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.UserCartItemAttributesOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartItemAttributeOptionRepository extends JpaRepository<UserCartItemAttributesOption, Integer> {
    List<UserCartItemAttributesOption> findByShoppingCartItemId(int id);
}
