package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.GoodsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategoryEntity, Long> {

    Optional<GoodsCategoryEntity> findBycategoryName(String category);
}
