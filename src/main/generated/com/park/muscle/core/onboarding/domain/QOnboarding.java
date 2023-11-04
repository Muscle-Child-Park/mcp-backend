package com.park.muscle.core.onboarding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOnboarding is a Querydsl query type for Onboarding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOnboarding extends EntityPathBase<Onboarding> {

    private static final long serialVersionUID = 858102555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOnboarding onboarding = new QOnboarding("onboarding");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    public final StringPath balance = createString("balance");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath eatingHabit = createString("eatingHabit");

    public final StringPath firstPurpose = createString("firstPurpose");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath interest = createString("interest");

    public final StringPath lifeStyle = createString("lifeStyle");

    public final com.park.muscle.core.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath secondPurpose = createString("secondPurpose");

    public QOnboarding(String variable) {
        this(Onboarding.class, forVariable(variable), INITS);
    }

    public QOnboarding(Path<? extends Onboarding> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOnboarding(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOnboarding(PathMetadata metadata, PathInits inits) {
        this(Onboarding.class, metadata, inits);
    }

    public QOnboarding(Class<? extends Onboarding> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.park.muscle.core.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

