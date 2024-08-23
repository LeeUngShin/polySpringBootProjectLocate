package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsSubCategoryEntity is a Querydsl query type for GoodsSubCategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsSubCategoryEntity extends EntityPathBase<GoodsSubCategoryEntity> {

    private static final long serialVersionUID = -1064193947L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoodsSubCategoryEntity goodsSubCategoryEntity = new QGoodsSubCategoryEntity("goodsSubCategoryEntity");

    public final StringPath categoryName = createString("categoryName");

    public final QGoodsCategoryEntity goodsCategoryEntity;

    public final ListPath<GoodsEntity, QGoodsEntity> goodsList = this.<GoodsEntity, QGoodsEntity>createList("goodsList", GoodsEntity.class, QGoodsEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public QGoodsSubCategoryEntity(String variable) {
        this(GoodsSubCategoryEntity.class, forVariable(variable), INITS);
    }

    public QGoodsSubCategoryEntity(Path<? extends GoodsSubCategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoodsSubCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoodsSubCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(GoodsSubCategoryEntity.class, metadata, inits);
    }

    public QGoodsSubCategoryEntity(Class<? extends GoodsSubCategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goodsCategoryEntity = inits.isInitialized("goodsCategoryEntity") ? new QGoodsCategoryEntity(forProperty("goodsCategoryEntity")) : null;
    }

}

