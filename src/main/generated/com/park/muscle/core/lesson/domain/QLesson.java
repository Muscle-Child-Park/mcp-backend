package com.park.muscle.core.lesson.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLesson is a Querydsl query type for Lesson
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLesson extends EntityPathBase<Lesson> {

    private static final long serialVersionUID = 1311948565L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLesson lesson = new QLesson("lesson");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    public final BooleanPath completionToggle = createBoolean("completionToggle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.park.muscle.core.exercise.domain.QExerciseDiary exerciseDiary;

    public final ListPath<com.park.muscle.core.exercise.domain.Exercise, com.park.muscle.core.exercise.domain.QExercise> exercises = this.<com.park.muscle.core.exercise.domain.Exercise, com.park.muscle.core.exercise.domain.QExercise>createList("exercises", com.park.muscle.core.exercise.domain.Exercise.class, com.park.muscle.core.exercise.domain.QExercise.class, PathInits.DIRECT2);

    public final StringPath feedback = createString("feedback");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lessonDate = createDateTime("lessonDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.park.muscle.core.reservation.domain.QReservation reservation;

    public final ListPath<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket> ticket = this.<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket>createList("ticket", com.park.muscle.core.ticket.domain.Ticket.class, com.park.muscle.core.ticket.domain.QTicket.class, PathInits.DIRECT2);

    public final StringPath timeSlot = createString("timeSlot");

    public QLesson(String variable) {
        this(Lesson.class, forVariable(variable), INITS);
    }

    public QLesson(Path<? extends Lesson> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLesson(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLesson(PathMetadata metadata, PathInits inits) {
        this(Lesson.class, metadata, inits);
    }

    public QLesson(Class<? extends Lesson> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseDiary = inits.isInitialized("exerciseDiary") ? new com.park.muscle.core.exercise.domain.QExerciseDiary(forProperty("exerciseDiary"), inits.get("exerciseDiary")) : null;
        this.reservation = inits.isInitialized("reservation") ? new com.park.muscle.core.reservation.domain.QReservation(forProperty("reservation")) : null;
    }

}

