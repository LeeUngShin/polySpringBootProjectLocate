package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByDelAndBoardEntityNumOrderByNumDesc(String del, Long boardNum);
}
