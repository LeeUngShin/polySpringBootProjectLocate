package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.GoodsCategoryEntity;
import com.example.polySpringBootProject.entity.GoodsSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsSubCategoryRepository extends JpaRepository<GoodsSubCategoryEntity, Long> {
    Optional<GoodsSubCategoryEntity> findByCategoryName(String category);

}
