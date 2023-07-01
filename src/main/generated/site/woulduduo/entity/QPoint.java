package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = 1384837212L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoint point = new QPoint("point");

    public final QAd ad;

    public final QAttendanceStamp attendanceStamp;

    public final QChatting chatting;

    public final QMatching matching;

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final NumberPath<Long> pointNo = createNumber("pointNo", Long.class);

    public final QUser user;

    public QPoint(String variable) {
        this(Point.class, forVariable(variable), INITS);
    }

    public QPoint(Path<? extends Point> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoint(PathMetadata metadata, PathInits inits) {
        this(Point.class, metadata, inits);
    }

    public QPoint(Class<? extends Point> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ad = inits.isInitialized("ad") ? new QAd(forProperty("ad")) : null;
        this.attendanceStamp = inits.isInitialized("attendanceStamp") ? new QAttendanceStamp(forProperty("attendanceStamp"), inits.get("attendanceStamp")) : null;
        this.chatting = inits.isInitialized("chatting") ? new QChatting(forProperty("chatting"), inits.get("chatting")) : null;
        this.matching = inits.isInitialized("matching") ? new QMatching(forProperty("matching"), inits.get("matching")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

