package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.GoodsEntity;
import com.example.polySpringBootProject.entity.LikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    List<LikeEntity> findByMemberId(String id);

    boolean existsByMemberNumAndGoodsNum(Long memberNum, Long goodsNum);
    boolean existsByMemberIdAndGoodsNum(String memberId, Long goodsNum);

    Optional<LikeEntity> findByMemberNumAndGoodsNum(Long memberNum, Long goodsNum);

    /*
    // 특정 회원과 상품에 대한 좋아요가 존재하는지 확인하는 JPQL 쿼리
    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.member.id = :memberId AND l.goods.id = :goodsId")
    boolean existsByMemberIdAndGoodsId(@Param("memberId") Long memberId, @Param("goodsId") Long goodsId);
    */

    @Query("select l.goods from LikeEntity l where l.member.id = :id")
    Page<LikeEntity> findByMemberId(Pageable pageable, @Param("id")String id);

    //Page<GoodsEntity> findByMemberId(Pageable pageable, String id);

    @Query("select l.goods from LikeEntity l where l.member.id = :id")
    List<GoodsEntity> findByMemberIdList(@Param("id")String id);


}