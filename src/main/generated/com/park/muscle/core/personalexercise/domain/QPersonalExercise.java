package com.park.muscle.core.personalexercise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalExercise is a Querydsl query type for PersonalExercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalExercise extends EntityPathBase<PersonalExercise> {

    private static final long serialVersionUID = 138150261L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalExercise personalExercise = new QPersonalExercise("personalExercise");

    public final EnumPath<com.park.muscle.core.exercise.domain.ClassType> classType = createEnum("classType", com.park.muscle.core.exercise.domain.ClassType.class);

    public final BooleanPath completionToggle = createBoolean("completionToggle");

    public final com.park.muscle.core.exercise.domain.QExerciseDiary exerciseDiary;

    public final ListPath<com.park.muscle.core.exercise.domain.Exercise, com.park.muscle.core.exercise.domain.QExercise> exercises = this.<com.park.muscle.core.exercise.domain.Exercise, com.park.muscle.core.exercise.domain.QExercise>createList("exercises", com.park.muscle.core.exercise.domain.Exercise.class, com.park.muscle.core.exercise.domain.QExercise.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lessonDate = createDateTime("lessonDate", java.time.LocalDateTime.class);

    public final StringPath timeSlot = createString("timeSlot");

    public QPersonalExercise(String variable) {
        this(PersonalExercise.class, forVariable(variable), INITS);
    }

    public QPersonalExercise(Path<? extends PersonalExercise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalExercise(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalExercise(PathMetadata metadata, PathInits inits) {
        this(PersonalExercise.class, metadata, inits);
    }

    public QPersonalExercise(Class<? extends PersonalExercise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseDiary = inits.isInitialized("exerciseDiary") ? new com.park.muscle.core.exercise.domain.QExerciseDiary(forProperty("exerciseDiary"), inits.get("exerciseDiary")) : null;
    }

}

