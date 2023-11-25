package com.park.muscle.core.trainer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayOff is a Querydsl query type for DayOff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayOff extends EntityPathBase<DayOff> {

    private static final long serialVersionUID = 1175038171L;

    public static final QDayOff dayOff = new QDayOff("dayOff");

    public final com.park.muscle.global.entity.QBaseEntity _super = new com.park.muscle.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath cycleStatus = createString("cycleStatus");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath timeSlot = createString("timeSlot");

    public QDayOff(String variable) {
        super(DayOff.class, forVariable(variable));
    }

    public QDayOff(Path<? extends DayOff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayOff(PathMetadata metadata) {
        super(DayOff.class, metadata);
    }

}

