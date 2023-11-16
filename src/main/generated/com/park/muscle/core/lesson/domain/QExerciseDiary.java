package com.park.muscle.core.lesson.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.park.muscle.core.exercise.domain.ExerciseDiary;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseDiary is a Querydsl query type for ExerciseDiary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseDiary extends EntityPathBase<ExerciseDiary> {

    private static final long serialVersionUID = -1469422514L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseDiary exerciseDiary = new QExerciseDiary("exerciseDiary");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> exerciseDate = createDateTime("exerciseDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.park.muscle.core.member.domain.QMember member;

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath status = createString("status");

    public final StringPath timeSlot = createString("timeSlot");

    public final StringPath title = createString("title");

    public QExerciseDiary(String variable) {
        this(ExerciseDiary.class, forVariable(variable), INITS);
    }

    public QExerciseDiary(Path<? extends ExerciseDiary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseDiary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseDiary(PathMetadata metadata, PathInits inits) {
        this(ExerciseDiary.class, metadata, inits);
    }

    public QExerciseDiary(Class<? extends ExerciseDiary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.park.muscle.core.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

