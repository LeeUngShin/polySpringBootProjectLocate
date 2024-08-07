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

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findAll(Pageable pageable);
    
    // BoardEntity의 member 변수(MemberEntity에 있는)의 id변수를 사용해 DB 검색
    Page<BoardEntity> findByMemberId(Pageable pageable, String Id);

    Page<BoardEntity> findByTitleContaining(Pageable pageable, String keyword);
    Page<BoardEntity> findByContentContaining(Pageable pageable, String keyword);
    @Query("select b from BoardEntity b where b.member.id = :id")
    Page<BoardEntity> findByWriterContaining(Pageable pageable, @Param("id") String keyword);
//    @Query("select b from BoardEntity b where b.member.id = :id")
//    Page<BoardEntity> findBySearchId(Pageable pageable, @Param("id") String id);

    List<BoardEntity> findByNoticeOrderByNumDesc(String notice);

    Page<BoardEntity> findByMemberIdAndTitleContaining(Pageable pageable, String id, String keyword);
    Page<BoardEntity> findByMemberIdAndContentContaining(Pageable pageable, String id, String keyword);
    // 게시글 조회 올리기
//    @Modifying  // update, delete 등 수정관련에 붙힘
//    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")  // 엔티티 기준
//    //@Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id", nativeQuery = true)  // db쿼리 기준
//    void updateHits(@Param("id") Long id);  // 매개변수의 Long id는 위 쿼리의 :id 자리에 대입됨

}
