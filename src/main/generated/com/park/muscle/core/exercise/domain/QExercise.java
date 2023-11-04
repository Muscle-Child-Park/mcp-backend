package com.park.muscle.core.exercise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExercise is a Querydsl query type for Exercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExercise extends EntityPathBase<Exercise> {

    private static final long serialVersionUID = -2110196011L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExercise exercise = new QExercise("exercise");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QExerciseDiary exerciseDiary;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public final StringPath weight = createString("weight");

    public QExercise(String variable) {
        this(Exercise.class, forVariable(variable), INITS);
    }

    public QExercise(Path<? extends Exercise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExercise(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExercise(PathMetadata metadata, PathInits inits) {
        this(Exercise.class, metadata, inits);
    }

    public QExercise(Class<? extends Exercise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseDiary = inits.isInitialized("exerciseDiary") ? new QExerciseDiary(forProperty("exerciseDiary"), inits.get("exerciseDiary")) : null;
    }

}

