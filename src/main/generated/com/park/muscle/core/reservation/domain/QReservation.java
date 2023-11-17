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

    public static final QReservation reservation = new QReservation("reservation");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> reservationCancelledTime = createDateTime("reservationCancelledTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> reservationDateTime = createDateTime("reservationDateTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> reservationModifiedTime = createDateTime("reservationModifiedTime", java.time.LocalDateTime.class);

    public final ListPath<ReserveTimeSlot, QReserveTimeSlot> reserveTimeSlots = this.<ReserveTimeSlot, QReserveTimeSlot>createList("reserveTimeSlots", ReserveTimeSlot.class, QReserveTimeSlot.class, PathInits.DIRECT2);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

