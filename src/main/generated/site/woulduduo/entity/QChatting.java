package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatting is a Querydsl query type for Chatting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatting extends EntityPathBase<Chatting> {

    private static final long serialVersionUID = -798139398L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatting chatting = new QChatting("chatting");

    public final QUser chattingFrom;

    public final NumberPath<Long> chattingNo = createNumber("chattingNo", Long.class);

    public final QUser chattingTo;

    public final ListPath<Matching, QMatching> matchingList = this.<Matching, QMatching>createList("matchingList", Matching.class, QMatching.class, PathInits.DIRECT2);

    public final ListPath<Message, QMessage> messageList = this.<Message, QMessage>createList("messageList", Message.class, QMessage.class, PathInits.DIRECT2);

    public final ListPath<Point, QPoint> pointList = this.<Point, QPoint>createList("pointList", Point.class, QPoint.class, PathInits.DIRECT2);

    public QChatting(String variable) {
        this(Chatting.class, forVariable(variable), INITS);
    }

    public QChatting(Path<? extends Chatting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatting(PathMetadata metadata, PathInits inits) {
        this(Chatting.class, metadata, inits);
    }

    public QChatting(Class<? extends Chatting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingFrom = inits.isInitialized("chattingFrom") ? new QUser(forProperty("chattingFrom")) : null;
        this.chattingTo = inits.isInitialized("chattingTo") ? new QUser(forProperty("chattingTo")) : null;
    }

}

