package com.park.muscle.core.lesson.domain;

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

    private static final long serialVersionUID = -1971974027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExercise exercise = new QExercise("exercise");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final EnumPath<ExerciseType> exerciseType = createEnum("exerciseType", ExerciseType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kind = createString("kind");

    public final QLesson lesson;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> runTime = createNumber("runTime", Integer.class);

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
        this.lesson = inits.isInitialized("lesson") ? new QLesson(forProperty("lesson"), inits.get("lesson")) : null;
    }

}

