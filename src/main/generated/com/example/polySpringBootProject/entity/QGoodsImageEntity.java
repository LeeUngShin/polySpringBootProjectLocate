package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsImageEntity is a Querydsl query type for GoodsImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsImageEntity extends EntityPathBase<GoodsImageEntity> {

    private static final long serialVersionUID = 669285282L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoodsImageEntity goodsImageEntity = new QGoodsImageEntity("goodsImageEntity");

    public final QGoodsEntity goodsEntity;

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    public final StringPath storedFileName = createString("storedFileName");

    public final StringPath storedFileNameWithExtension = createString("storedFileNameWithExtension");

    public final StringPath uploadPath = createString("uploadPath");

    public QGoodsImageEntity(String variable) {
        this(GoodsImageEntity.class, forVariable(variable), INITS);
    }

    public QGoodsImageEntity(Path<? extends GoodsImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoodsImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoodsImageEntity(PathMetadata metadata, PathInits inits) {
        this(GoodsImageEntity.class, metadata, inits);
    }

    public QGoodsImageEntity(Class<? extends GoodsImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goodsEntity = inits.isInitialized("goodsEntity") ? new QGoodsEntity(forProperty("goodsEntity"), inits.get("goodsEntity")) : null;
    }

}

