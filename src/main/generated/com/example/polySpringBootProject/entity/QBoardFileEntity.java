package com.example.polySpringBootProject.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardFileEntity is a Querydsl query type for BoardFileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardFileEntity extends EntityPathBase<BoardFileEntity> {

    private static final long serialVersionUID = 1475594507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardFileEntity boardFileEntity = new QBoardFileEntity("boardFileEntity");

    public final QBoardEntity boardEntity;

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    public final StringPath storedFileName = createString("storedFileName");

    public final StringPath storedFileNameWithExtension = createString("storedFileNameWithExtension");

    public final StringPath uploadPath = createString("uploadPath");

    public QBoardFileEntity(String variable) {
        this(BoardFileEntity.class, forVariable(variable), INITS);
    }

    public QBoardFileEntity(Path<? extends BoardFileEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardFileEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardFileEntity(PathMetadata metadata, PathInits inits) {
        this(BoardFileEntity.class, metadata, inits);
    }

    public QBoardFileEntity(Class<? extends BoardFileEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardEntity = inits.isInitialized("boardEntity") ? new QBoardEntity(forProperty("boardEntity"), inits.get("boardEntity")) : null;
    }

}

