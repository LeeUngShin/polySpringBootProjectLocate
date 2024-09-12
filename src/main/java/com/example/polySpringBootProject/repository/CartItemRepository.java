package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findByCartEntityMemberId(String id);

    boolean existsByGoodsNum(Long goodsNum);

    Optional<CartItemEntity> findByCartEntityNumAndGoodsNum(Long cartNum, Long goodsNum);

    boolean existsByCartEntityMemberIdAndGoodsNum(String id, Long goodsNum);

}
