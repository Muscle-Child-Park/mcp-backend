package com.park.muscle.core.trainer.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDayOff is a Querydsl query type for DayOff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayOff extends EntityPathBase<DayOff> {

    private static final long serialVersionUID = 1175038171L;

    private static final PathInits INITS = PathInits.DIRECT2;

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

    public final QTrainer trainer;

    public QDayOff(String variable) {
        this(DayOff.class, forVariable(variable), INITS);
    }

    public QDayOff(Path<? extends DayOff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDayOff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDayOff(PathMetadata metadata, PathInits inits) {
        this(DayOff.class, metadata, inits);
    }

    public QDayOff(Class<? extends DayOff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainer = inits.isInitialized("trainer") ? new QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

