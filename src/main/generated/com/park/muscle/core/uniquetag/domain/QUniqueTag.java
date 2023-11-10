package com.park.muscle.core.uniquetag.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUniqueTag is a Querydsl query type for UniqueTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUniqueTag extends EntityPathBase<UniqueTag> {

    private static final long serialVersionUID = 148377389L;

    public static final QUniqueTag uniqueTag1 = new QUniqueTag("uniqueTag1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<java.util.UUID> uniqueTag = createComparable("uniqueTag", java.util.UUID.class);

    public QUniqueTag(String variable) {
        super(UniqueTag.class, forVariable(variable));
    }

    public QUniqueTag(Path<? extends UniqueTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUniqueTag(PathMetadata metadata) {
        super(UniqueTag.class, metadata);
    }

}

