package com.park.muscle.core.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -715215059L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    public final BooleanPath confirm = createBoolean("confirm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> reservationCancelledTime = createDateTime("reservationCancelledTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> reservationDateTime = createDateTime("reservationDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> reservationModifiedTime = createDateTime("reservationModifiedTime", java.time.LocalDateTime.class);

    public final ListPath<ReserveTimeSlot, QReserveTimeSlot> reserveTimeSlots = this.<ReserveTimeSlot, QReserveTimeSlot>createList("reserveTimeSlots", ReserveTimeSlot.class, QReserveTimeSlot.class, PathInits.DIRECT2);

    public final com.park.muscle.core.ticket.domain.QTicket ticket;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ticket = inits.isInitialized("ticket") ? new com.park.muscle.core.ticket.domain.QTicket(forProperty("ticket"), inits.get("ticket")) : null;
    }

}

