package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    boolean existsByMemberId(String id);

    CartEntity findByMemberId(String id);
}
