package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findAll(Pageable pageable);

    Page<BoardEntity> findByTitleContaining(Pageable pageable, String keyword);

    // 게시글 조회 올리기

//    @Modifying  // update, delete 등 수정관련에 붙힘
//    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")  // 엔티티 기준
//    //@Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id", nativeQuery = true)  // db쿼리 기준
//    void updateHits(@Param("id") Long id);  // 매개변수의 Long id는 위 쿼리의 :id 자리에 대입됨

}
