package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {

    @Query("select m from BoardFileEntity m where m.boardEntity.num = :boardNum")
    Optional<BoardFileEntity> findByBoardNum(@Param("boardNum") Long boardNum);
}
