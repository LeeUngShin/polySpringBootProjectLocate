package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 1026213079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final EnumPath<com.example.polySpringBootProject.enumClass.DeliveryType> deliveryType = createEnum("deliveryType", com.example.polySpringBootProject.enumClass.DeliveryType.class);

    public final QGoodsEntity goods;

    public final QMemberEntity member;

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final NumberPath<Long> orderUniqueNumber = createNumber("orderUniqueNumber", Long.class);

    public final EnumPath<com.example.polySpringBootProject.enumClass.PaymentMethod> paymentMethod = createEnum("paymentMethod", com.example.polySpringBootProject.enumClass.PaymentMethod.class);

    public final NumberPath<Integer> submitAccumulatedMoney = createNumber("submitAccumulatedMoney", Integer.class);

    public final StringPath submitAddr = createString("submitAddr");

    public final StringPath submitAddrDetail = createString("submitAddrDetail");

    public final NumberPath<Integer> submitAmount = createNumber("submitAmount", Integer.class);

    public final NumberPath<Integer> submitDeliveryPrice = createNumber("submitDeliveryPrice", Integer.class);

    public final NumberPath<Integer> submitFinalPrice = createNumber("submitFinalPrice", Integer.class);

    public final StringPath submitOrderMessageChoice = createString("submitOrderMessageChoice");

    public final StringPath submitPost = createString("submitPost");

    public final NumberPath<Integer> submitUseAccumulatedMoney = createNumber("submitUseAccumulatedMoney", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QOrderEntity(String variable) {
        this(OrderEntity.class, forVariable(variable), INITS);
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderEntity(PathMetadata metadata, PathInits inits) {
        this(OrderEntity.class, metadata, inits);
    }

    public QOrderEntity(Class<? extends OrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goods = inits.isInitialized("goods") ? new QGoodsEntity(forProperty("goods"), inits.get("goods")) : null;
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member")) : null;
    }

}

