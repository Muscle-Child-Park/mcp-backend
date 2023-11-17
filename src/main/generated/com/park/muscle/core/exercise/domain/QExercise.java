package com.park.muscle.core.exercise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExercise is a Querydsl query type for Exercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExercise extends EntityPathBase<Exercise> {

    private static final long serialVersionUID = -2110196011L;

    public static final QExercise exercise = new QExercise("exercise");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final EnumPath<ExerciseType> exerciseType = createEnum("exerciseType", ExerciseType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kind = createString("kind");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> runTime = createNumber("runTime", Integer.class);

    public final StringPath weight = createString("weight");

    public QExercise(String variable) {
        super(Exercise.class, forVariable(variable));
    }

    public QExercise(Path<? extends Exercise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExercise(PathMetadata metadata) {
        super(Exercise.class, metadata);
    }

}

