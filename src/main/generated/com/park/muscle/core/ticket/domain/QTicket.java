package com.park.muscle.core.ticket.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = 1288140733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTicket ticket = new QTicket("ticket");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> leftQuantity = createNumber("leftQuantity", Integer.class);

    public final ListPath<com.park.muscle.core.lesson.domain.Lesson, com.park.muscle.core.lesson.domain.QLesson> lessons = this.<com.park.muscle.core.lesson.domain.Lesson, com.park.muscle.core.lesson.domain.QLesson>createList("lessons", com.park.muscle.core.lesson.domain.Lesson.class, com.park.muscle.core.lesson.domain.QLesson.class, PathInits.DIRECT2);

    public final com.park.muscle.core.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> totalQuantity = createNumber("totalQuantity", Integer.class);

    public final com.park.muscle.core.trainer.domain.QTrainer trainer;

    public QTicket(String variable) {
        this(Ticket.class, forVariable(variable), INITS);
    }

    public QTicket(Path<? extends Ticket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTicket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTicket(PathMetadata metadata, PathInits inits) {
        this(Ticket.class, metadata, inits);
    }

    public QTicket(Class<? extends Ticket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.park.muscle.core.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.trainer = inits.isInitialized("trainer") ? new com.park.muscle.core.trainer.domain.QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

