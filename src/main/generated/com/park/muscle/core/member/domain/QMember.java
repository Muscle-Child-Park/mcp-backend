package com.park.muscle.core.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -661756327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<com.park.muscle.core.exercise.domain.ExerciseDiary, com.park.muscle.core.exercise.domain.QExerciseDiary> diaries = this.<com.park.muscle.core.exercise.domain.ExerciseDiary, com.park.muscle.core.exercise.domain.QExerciseDiary>createList("diaries", com.park.muscle.core.exercise.domain.ExerciseDiary.class, com.park.muscle.core.exercise.domain.QExerciseDiary.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final QName name;

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.park.muscle.global.enumerate.SocialType> socialType = createEnum("socialType", com.park.muscle.global.enumerate.SocialType.class);

    public final ListPath<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket> tickets = this.<com.park.muscle.core.ticket.domain.Ticket, com.park.muscle.core.ticket.domain.QTicket>createList("tickets", com.park.muscle.core.ticket.domain.Ticket.class, com.park.muscle.core.ticket.domain.QTicket.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.name = inits.isInitialized("name") ? new QName(forProperty("name")) : null;
    }

}

