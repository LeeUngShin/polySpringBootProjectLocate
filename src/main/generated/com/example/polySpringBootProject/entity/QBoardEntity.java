package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardEntity is a Querydsl query type for BoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardEntity extends EntityPathBase<BoardEntity> {

    private static final long serialVersionUID = 1975261807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardEntity boardEntity = new QBoardEntity("boardEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<BoardFileEntity, QBoardFileEntity> boardFileEntities = this.<BoardFileEntity, QBoardFileEntity>createList("boardFileEntities", BoardFileEntity.class, QBoardFileEntity.class, PathInits.DIRECT2);

    public final EnumPath<com.example.polySpringBootProject.enumClass.BoardType> boardType = createEnum("boardType", com.example.polySpringBootProject.enumClass.BoardType.class);

    public final ListPath<CommentEntity, QCommentEntity> commentEntityList = this.<CommentEntity, QCommentEntity>createList("commentEntityList", CommentEntity.class, QCommentEntity.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final StringPath del = createString("del");

    public final NumberPath<Integer> fileAttached = createNumber("fileAttached", Integer.class);

    public final QMemberEntity member;

    public final StringPath notice = createString("notice");

    public final StringPath noticeTop = createString("noticeTop");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath secret = createString("secret");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedTime = _super.updatedTime;

    public QBoardEntity(String variable) {
        this(BoardEntity.class, forVariable(variable), INITS);
    }

    public QBoardEntity(Path<? extends BoardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardEntity(PathMetadata metadata, PathInits inits) {
        this(BoardEntity.class, metadata, inits);
    }

    public QBoardEntity(Class<? extends BoardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member")) : null;
    }

}

