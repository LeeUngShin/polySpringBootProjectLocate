package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.GoodsImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodsImageRepository extends JpaRepository<GoodsImageEntity, Long> {

    Optional<GoodsImageEntity> findByGoodsEntityNum(Long goodsNum);
}
