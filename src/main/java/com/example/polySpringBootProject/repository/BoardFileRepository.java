package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
