package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {

    int countByName(String name);
    //List<BoardEntity> findTop5OrderBySellCntASC
}
