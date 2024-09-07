package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // 로그인 회원 전체 주문 페이징
    Page<OrderEntity> findByMemberId(Pageable pageable, String loginId);
}
