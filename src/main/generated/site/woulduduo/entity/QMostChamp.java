package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMostChamp is a Querydsl query type for MostChamp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMostChamp extends EntityPathBase<MostChamp> {

    private static final long serialVersionUID = -1802519992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMostChamp mostChamp = new QMostChamp("mostChamp");

    public final StringPath champName = createString("champName");

    public final NumberPath<Long> champNo = createNumber("champNo", Long.class);

    public final NumberPath<Integer> mostNo = createNumber("mostNo", Integer.class);

    public final QUser user;

    public QMostChamp(String variable) {
        this(MostChamp.class, forVariable(variable), INITS);
    }

    public QMostChamp(Path<? extends MostChamp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMostChamp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMostChamp(PathMetadata metadata, PathInits inits) {
        this(MostChamp.class, metadata, inits);
    }

    public QMostChamp(Class<? extends MostChamp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

