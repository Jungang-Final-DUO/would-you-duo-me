package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatching is a Querydsl query type for Matching
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatching extends EntityPathBase<Matching> {

    private static final long serialVersionUID = -1939567183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatching matching = new QMatching("matching");

    public final QChatting chatting;

    public final DatePath<java.time.LocalDate> matchingDate = createDate("matchingDate", java.time.LocalDate.class);

    public final NumberPath<Long> matchingNo = createNumber("matchingNo", Long.class);

    public final NumberPath<Integer> matchingPoint = createNumber("matchingPoint", Integer.class);

    public final StringPath matchingReviewContent = createString("matchingReviewContent");

    public final NumberPath<Integer> matchingReviewRate = createNumber("matchingReviewRate", Integer.class);

    public final EnumPath<site.woulduduo.enumeration.MatchingStatus> matchingStatus = createEnum("matchingStatus", site.woulduduo.enumeration.MatchingStatus.class);

    public final ListPath<Point, QPoint> pointList = this.<Point, QPoint>createList("pointList", Point.class, QPoint.class, PathInits.DIRECT2);

    public QMatching(String variable) {
        this(Matching.class, forVariable(variable), INITS);
    }

    public QMatching(Path<? extends Matching> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatching(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatching(PathMetadata metadata, PathInits inits) {
        this(Matching.class, metadata, inits);
    }

    public QMatching(Class<? extends Matching> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatting = inits.isInitialized("chatting") ? new QChatting(forProperty("chatting"), inits.get("chatting")) : null;
    }

}

