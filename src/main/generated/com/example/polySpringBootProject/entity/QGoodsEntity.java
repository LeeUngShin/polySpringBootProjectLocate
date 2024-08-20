package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoodsEntity is a Querydsl query type for GoodsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsEntity extends EntityPathBase<GoodsEntity> {

    private static final long serialVersionUID = -450973985L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoodsEntity goodsEntity = new QGoodsEntity("goodsEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final StringPath del = createString("del");

    public final StringPath explanation = createString("explanation");

    public final QGoodsCategoryEntity goodsCategory;

    public final QGoodsImageEntity goodsImageEntity;

    public final NumberPath<Integer> likeCnt = createNumber("likeCnt", Integer.class);

    public final SetPath<LikeEntity, QLikeEntity> likeEntitySet = this.<LikeEntity, QLikeEntity>createSet("likeEntitySet", LikeEntity.class, QLikeEntity.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> sellCnt = createNumber("sellCnt", Integer.class);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QGoodsEntity(String variable) {
        this(GoodsEntity.class, forVariable(variable), INITS);
    }

    public QGoodsEntity(Path<? extends GoodsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoodsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoodsEntity(PathMetadata metadata, PathInits inits) {
        this(GoodsEntity.class, metadata, inits);
    }

    public QGoodsEntity(Class<? extends GoodsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goodsCategory = inits.isInitialized("goodsCategory") ? new QGoodsCategoryEntity(forProperty("goodsCategory")) : null;
        this.goodsImageEntity = inits.isInitialized("goodsImageEntity") ? new QGoodsImageEntity(forProperty("goodsImageEntity"), inits.get("goodsImageEntity")) : null;
    }

}

