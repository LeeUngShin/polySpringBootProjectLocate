package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
