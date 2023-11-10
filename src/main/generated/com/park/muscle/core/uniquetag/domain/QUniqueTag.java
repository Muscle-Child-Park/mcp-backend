package com.park.muscle.core.uniquetag.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUniqueTag is a Querydsl query type for UniqueTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUniqueTag extends EntityPathBase<UniqueTag> {

    private static final long serialVersionUID = 148377389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUniqueTag uniqueTag1 = new QUniqueTag("uniqueTag1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.park.muscle.core.member.domain.QMember member;

    public final com.park.muscle.core.trainer.domain.QTrainer trainer;

    public final StringPath uniqueTag = createString("uniqueTag");

    public QUniqueTag(String variable) {
        this(UniqueTag.class, forVariable(variable), INITS);
    }

    public QUniqueTag(Path<? extends UniqueTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUniqueTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUniqueTag(PathMetadata metadata, PathInits inits) {
        this(UniqueTag.class, metadata, inits);
    }

    public QUniqueTag(Class<? extends UniqueTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.park.muscle.core.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.trainer = inits.isInitialized("trainer") ? new com.park.muscle.core.trainer.domain.QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

