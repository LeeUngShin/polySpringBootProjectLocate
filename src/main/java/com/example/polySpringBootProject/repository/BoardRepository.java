package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findAll(Pageable pageable);

    Page<BoardEntity> findByTitleContaining(Pageable pageable, String keyword);

}
