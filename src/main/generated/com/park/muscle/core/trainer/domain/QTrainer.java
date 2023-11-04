package com.park.muscle.core.trainer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrainer is a Querydsl query type for Trainer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainer extends EntityPathBase<Trainer> {

    private static final long serialVersionUID = -448051955L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrainer trainer = new QTrainer("trainer");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<DayOff, QDayOff> dayOffs = this.<DayOff, QDayOff>createList("dayOffs", DayOff.class, QDayOff.class, PathInits.DIRECT2);

    public final QGym gym;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath social = createString("social");

    public final EnumPath<com.park.muscle.global.enumerate.SocialType> socialType = createEnum("socialType", com.park.muscle.global.enumerate.SocialType.class);

    public final ListPath<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket> tickets = this.<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket>createList("tickets", com.park.muscle.core.ticket.domain.Ticket.class, com.park.muscle.core.ticket.domain.QTicket.class, PathInits.DIRECT2);

    public final StringPath trainerTag = createString("trainerTag");

    public QTrainer(String variable) {
        this(Trainer.class, forVariable(variable), INITS);
    }

    public QTrainer(Path<? extends Trainer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrainer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrainer(PathMetadata metadata, PathInits inits) {
        this(Trainer.class, metadata, inits);
    }

    public QTrainer(Class<? extends Trainer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gym = inits.isInitialized("gym") ? new QGym(forProperty("gym")) : null;
    }

}

