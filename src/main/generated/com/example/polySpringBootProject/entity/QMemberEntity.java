package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 1656217559L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> accumulatedMoney = createNumber("accumulatedMoney", Integer.class);

    public final StringPath addr = createString("addr");

    public final StringPath addrDetail = createString("addrDetail");

    public final StringPath approval = createString("approval");

    public final ListPath<BoardEntity, QBoardEntity> boardDatas = this.<BoardEntity, QBoardEntity>createList("boardDatas", BoardEntity.class, QBoardEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final StringPath email = createString("email");

    public final EnumPath<com.example.polySpringBootProject.enumClass.MemberGrade> grade = createEnum("grade", com.example.polySpringBootProject.enumClass.MemberGrade.class);

    public final StringPath id = createString("id");

    public final SetPath<LikeEntity, QLikeEntity> likeEntitySet = this.<LikeEntity, QLikeEntity>createSet("likeEntitySet", LikeEntity.class, QLikeEntity.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final ListPath<OrderEntity, QOrderEntity> orderEntityList = this.<OrderEntity, QOrderEntity>createList("orderEntityList", OrderEntity.class, QOrderEntity.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    public final StringPath post = createString("post");

    public final StringPath pw = createString("pw");

    public final EnumPath<com.example.polySpringBootProject.enumClass.RoleType> role = createEnum("role", com.example.polySpringBootProject.enumClass.RoleType.class);

    public final NumberPath<Integer> sellTotalPrice = createNumber("sellTotalPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

