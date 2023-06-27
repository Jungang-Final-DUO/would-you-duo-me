package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccuse is a Querydsl query type for Accuse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccuse extends EntityPathBase<Accuse> {

    private static final long serialVersionUID = -460410854L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccuse accuse = new QAccuse("accuse");

    public final StringPath accuseEtc = createString("accuseEtc");

    public final NumberPath<Long> accuseNo = createNumber("accuseNo", Long.class);

    public final StringPath accuseType = createString("accuseType");

    public final DateTimePath<java.time.LocalDateTime> accuseWrittenDate = createDateTime("accuseWrittenDate", java.time.LocalDateTime.class);

    public final QUser user;

    public QAccuse(String variable) {
        this(Accuse.class, forVariable(variable), INITS);
    }

    public QAccuse(Path<? extends Accuse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccuse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccuse(PathMetadata metadata, PathInits inits) {
        this(Accuse.class, metadata, inits);
    }

    public QAccuse(Class<? extends Accuse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

