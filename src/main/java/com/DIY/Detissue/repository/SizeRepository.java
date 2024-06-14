package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

}
