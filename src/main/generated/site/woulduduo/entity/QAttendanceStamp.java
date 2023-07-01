package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceStamp is a Querydsl query type for AttendanceStamp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceStamp extends EntityPathBase<AttendanceStamp> {

    private static final long serialVersionUID = 500753958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceStamp attendanceStamp = new QAttendanceStamp("attendanceStamp");

    public final ListPath<Point, QPoint> pointList = this.<Point, QPoint>createList("pointList", Point.class, QPoint.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> stampMoth = createDate("stampMoth", java.time.LocalDate.class);

    public final NumberPath<Long> stampNo = createNumber("stampNo", Long.class);

    public final NumberPath<Integer> stampType = createNumber("stampType", Integer.class);

    public final QUser user;

    public QAttendanceStamp(String variable) {
        this(AttendanceStamp.class, forVariable(variable), INITS);
    }

    public QAttendanceStamp(Path<? extends AttendanceStamp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceStamp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceStamp(PathMetadata metadata, PathInits inits) {
        this(AttendanceStamp.class, metadata, inits);
    }

    public QAttendanceStamp(Class<? extends AttendanceStamp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

