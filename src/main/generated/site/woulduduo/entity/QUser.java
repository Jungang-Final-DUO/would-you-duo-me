package site.woulduduo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -370817153L;

    public static final QUser user = new QUser("user");

    public final ListPath<Accuse, QAccuse> accuseList = this.<Accuse, QAccuse>createList("accuseList", Accuse.class, QAccuse.class, PathInits.DIRECT2);

    public final ListPath<Attendance, QAttendance> attendanceList = this.<Attendance, QAttendance>createList("attendanceList", Attendance.class, QAttendance.class, PathInits.DIRECT2);

    public final ListPath<AttendanceStamp, QAttendanceStamp> attendanceStampList = this.<AttendanceStamp, QAttendanceStamp>createList("attendanceStampList", AttendanceStamp.class, QAttendanceStamp.class, PathInits.DIRECT2);

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final ListPath<Chatting, QChatting> chattingFromList = this.<Chatting, QChatting>createList("chattingFromList", Chatting.class, QChatting.class, PathInits.DIRECT2);

    public final ListPath<Chatting, QChatting> chattingToList = this.<Chatting, QChatting>createList("chattingToList", Chatting.class, QChatting.class, PathInits.DIRECT2);

    public final ListPath<Follow, QFollow> followFromList = this.<Follow, QFollow>createList("followFromList", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final ListPath<Follow, QFollow> followToList = this.<Follow, QFollow>createList("followToList", Follow.class, QFollow.class, PathInits.DIRECT2);

    public final StringPath lolNickname = createString("lolNickname");

    public final EnumPath<site.woulduduo.enumeration.Tier> lolTier = createEnum("lolTier", site.woulduduo.enumeration.Tier.class);

    public final ListPath<MostChamp, QMostChamp> mostChampList = this.<MostChamp, QMostChamp>createList("mostChampList", MostChamp.class, QMostChamp.class, PathInits.DIRECT2);

    public final ListPath<Point, QPoint> pointList = this.<Point, QPoint>createList("pointList", Point.class, QPoint.class, PathInits.DIRECT2);

    public final ListPath<Reply, QReply> replyList = this.<Reply, QReply>createList("replyList", Reply.class, QReply.class, PathInits.DIRECT2);

    public final StringPath userAccount = createString("userAccount");

    public final NumberPath<Double> userAvgRate = createNumber("userAvgRate", Double.class);

    public final DatePath<java.time.LocalDate> userBirthday = createDate("userBirthday", java.time.LocalDate.class);

    public final StringPath userComment = createString("userComment");

    public final DateTimePath<java.time.LocalDateTime> userCookieLimitTime = createDateTime("userCookieLimitTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> userCurrentPoint = createNumber("userCurrentPoint", Integer.class);

    public final StringPath userFacebook = createString("userFacebook");

    public final EnumPath<site.woulduduo.enumeration.Gender> userGender = createEnum("userGender", site.woulduduo.enumeration.Gender.class);

    public final StringPath userInstagram = createString("userInstagram");

    public final BooleanPath userIsBanned = createBoolean("userIsBanned");

    public final DatePath<java.time.LocalDate> userJoinDate = createDate("userJoinDate", java.time.LocalDate.class);

    public final EnumPath<site.woulduduo.enumeration.LoginType> userLoginType = createEnum("userLoginType", site.woulduduo.enumeration.LoginType.class);

    public final NumberPath<Integer> userMatchingPoint = createNumber("userMatchingPoint", Integer.class);

    public final StringPath userNickname = createString("userNickname");

    public final StringPath userPassword = createString("userPassword");

    public final EnumPath<site.woulduduo.enumeration.Position> userPosition = createEnum("userPosition", site.woulduduo.enumeration.Position.class);

    public final ListPath<UserProfile, QUserProfile> userProfileList = this.<UserProfile, QUserProfile>createList("userProfileList", UserProfile.class, QUserProfile.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> userRecentLoginDate = createDateTime("userRecentLoginDate", java.time.LocalDateTime.class);

    public final StringPath userSessionId = createString("userSessionId");

    public final StringPath userTwitter = createString("userTwitter");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

