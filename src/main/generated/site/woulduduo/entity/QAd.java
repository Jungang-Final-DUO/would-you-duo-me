package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAd is a Querydsl query type for Ad
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAd extends EntityPathBase<Ad> {

    private static final long serialVersionUID = 1465533655L;

    public static final QAd ad = new QAd("ad");

    public final StringPath adImage = createString("adImage");

    public final NumberPath<Long> adNo = createNumber("adNo", Long.class);

    public final NumberPath<Integer> adPoint = createNumber("adPoint", Integer.class);

    public final StringPath adTitle = createString("adTitle");

    public final EnumPath<site.woulduduo.enumeration.AdType> adType = createEnum("adType", site.woulduduo.enumeration.AdType.class);

    public final ListPath<Point, QPoint> pointList = this.<Point, QPoint>createList("pointList", Point.class, QPoint.class, PathInits.DIRECT2);

    public QAd(String variable) {
        super(Ad.class, forVariable(variable));
    }

    public QAd(Path<? extends Ad> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAd(PathMetadata metadata) {
        super(Ad.class, metadata);
    }

}

