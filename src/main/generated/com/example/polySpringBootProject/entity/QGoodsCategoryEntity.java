package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsCategoryEntity is a Querydsl query type for GoodsCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsCategoryEntity extends EntityPathBase<GoodsCategoryEntity> {

    private static final long serialVersionUID = -353671811L;

    public static final QGoodsCategoryEntity goodsCategoryEntity = new QGoodsCategoryEntity("goodsCategoryEntity");

    public final StringPath categoryName = createString("categoryName");

    public final ListPath<GoodsEntity, QGoodsEntity> goodsList = this.<GoodsEntity, QGoodsEntity>createList("goodsList", GoodsEntity.class, QGoodsEntity.class, PathInits.DIRECT2);

    public final ListPath<GoodsSubCategoryEntity, QGoodsSubCategoryEntity> goodsSubCategoryEntityList = this.<GoodsSubCategoryEntity, QGoodsSubCategoryEntity>createList("goodsSubCategoryEntityList", GoodsSubCategoryEntity.class, QGoodsSubCategoryEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public QGoodsCategoryEntity(String variable) {
        super(GoodsCategoryEntity.class, forVariable(variable));
    }

    public QGoodsCategoryEntity(Path<? extends GoodsCategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsCategoryEntity(PathMetadata metadata) {
        super(GoodsCategoryEntity.class, metadata);
    }

}

