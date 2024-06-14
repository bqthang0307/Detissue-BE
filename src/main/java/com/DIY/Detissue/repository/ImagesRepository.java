package com.DIY.Detissue.repository;

import com.DIY.Detissue.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Integer> {
    List<Image> findByProductId(int id);
}
