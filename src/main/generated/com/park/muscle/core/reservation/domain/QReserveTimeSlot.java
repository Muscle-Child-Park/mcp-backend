package com.park.muscle.core.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReserveTimeSlot is a Querydsl query type for ReserveTimeSlot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReserveTimeSlot extends EntityPathBase<ReserveTimeSlot> {

    private static final long serialVersionUID = -334214616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReserveTimeSlot reserveTimeSlot = new QReserveTimeSlot("reserveTimeSlot");

    public final BooleanPath accessibleTime = createBoolean("accessibleTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReservation reservation;

    public final TimePath<java.time.LocalTime> time = createTime("time", java.time.LocalTime.class);

    public QReserveTimeSlot(String variable) {
        this(ReserveTimeSlot.class, forVariable(variable), INITS);
    }

    public QReserveTimeSlot(Path<? extends ReserveTimeSlot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReserveTimeSlot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReserveTimeSlot(PathMetadata metadata, PathInits inits) {
        this(ReserveTimeSlot.class, metadata, inits);
    }

    public QReserveTimeSlot(Class<? extends ReserveTimeSlot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

