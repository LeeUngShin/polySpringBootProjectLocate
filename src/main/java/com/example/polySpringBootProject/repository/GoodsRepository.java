package com.example.polySpringBootProject.repository;

import com.example.polySpringBootProject.entity.BoardEntity;
import com.example.polySpringBootProject.entity.GoodsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
    
    
    int countByName(String name);  // 중복이름 확인
    Page<GoodsEntity> findAllByDel(Pageable pageable, String del);  // 목록 페이징

    Page<GoodsEntity> findAll(Pageable pageable);

    Page<GoodsEntity> findByGoodsCategoryCategoryName(Pageable pageable, String categoryName);
    Page<GoodsEntity> findByGoodsSubCategoryCategoryName(Pageable pageable, String subCategoryName);

    //@Query()
    //List<GoodsEntity> findByGoodsCategoryCategoryName(String topCategory);

    List<GoodsEntity> findTop4ByGoodsCategoryCategoryNameOrderBySellCntDescLikeCntDesc(String topCategory);

    //@Query()
    //List<GoodsEntity> findByGoodsSubCategoryCategoryNameTop4OrderBySellCntDescLikeCntDesc(String topCategory);

    List<GoodsEntity> findTop4ByGoodsSubCategoryCategoryNameOrderBySellCntDescLikeCntDesc(String topCategory);

    Optional<GoodsEntity> findByName(String goodsName);




    //List<GoodsEntity> findByGoodsCategoryCategoryName(String categoryName);
    //List<GoodsEntity> findByGoodsSubCategoryCategoryName(String subCategoryName);

}
