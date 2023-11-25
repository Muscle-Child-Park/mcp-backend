package com.park.muscle.core.exercise.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseDiary is a Querydsl query type for ExerciseDiary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseDiary extends EntityPathBase<ExerciseDiary> {

    private static final long serialVersionUID = 1482193390L;

    public static final QExerciseDiary exerciseDiary = new QExerciseDiary("exerciseDiary");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QExerciseDiary(String variable) {
        super(ExerciseDiary.class, forVariable(variable));
    }

    public QExerciseDiary(Path<? extends ExerciseDiary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseDiary(PathMetadata metadata) {
        super(ExerciseDiary.class, metadata);
    }

}

