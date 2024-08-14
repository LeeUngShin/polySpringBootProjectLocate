package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {

    int countByName(String name);
}
