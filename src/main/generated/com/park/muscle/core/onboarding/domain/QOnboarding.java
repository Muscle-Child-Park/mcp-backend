package com.park.muscle.core.onboarding.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOnboarding is a Querydsl query type for Onboarding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOnboarding extends EntityPathBase<Onboarding> {

    private static final long serialVersionUID = 858102555L;

    public static final QOnboarding onboarding = new QOnboarding("onboarding");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    public final StringPath balance = createString("balance");

    public final StringPath bodyPurpose = createString("bodyPurpose");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath eatingHabit = createString("eatingHabit");

    public final StringPath exercisePurpose = createString("exercisePurpose");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath interest = createString("interest");

    public final StringPath lifeStyle = createString("lifeStyle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public QOnboarding(String variable) {
        super(Onboarding.class, forVariable(variable));
    }

    public QOnboarding(Path<? extends Onboarding> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOnboarding(PathMetadata metadata) {
        super(Onboarding.class, metadata);
    }

}

