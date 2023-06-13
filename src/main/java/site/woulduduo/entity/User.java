package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.LoginType;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static site.woulduduo.enumeration.LoginType.NORMAL;

@Setter
@Getter
@ToString(exclude = {"replyList", "userProfileList", "attendanceList", "accuseList"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userAccount")
@Builder
@Entity
@Table(name = "duo_user")
public class User {

    @Id
    @Column(name = "user_account", length = 50)
    private String userAccount;

    @Column(name = "user_nickname", length = 15, nullable = false, unique = true)
    private String userNickname;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_birthday", nullable = false)
    @Check(constraints = "TIMESTAMPDIFF(YEAR, user_birthday, CURDATE()) > 18")
    private LocalDate userBirthday;

    @Column(name = "lol_ninkname", length = 20, nullable = false, unique = true)
    private String lolNickname;

    @Column(name = "user_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender userGender;

    @CreationTimestamp
    @Column(name = "user_join_date", updatable = false)
    private LocalDate userJoinDate;

    private LocalDateTime userRecentLoginDate;

    @Builder.Default
    @Column(columnDefinition = "TINYINT(1)")
    private boolean userIsBanned = false;

    @Enumerated(EnumType.STRING)
    private Position userPosition;

    @Column(length = 100)
    private String userComment;

    @Column(length = 3)
    private Integer userMatchingPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tier lolTier;

    @Builder.Default
    private Integer userCurrentPoint = 0;

    @Column(length = 30, unique = true)
    private String userTwitter;

    @Column(length = 30, unique = true)
    private String userFacebook;

    @Column(length = 30, unique = true)
    private String userInstagram;

    @Builder.Default
    @Column(columnDefinition = "DOUBLE(2, 1)")
    private Double userAvgRate = 0.0;

    private LocalDateTime userCookieLimitTime;

    @Column(length = 200)
    @Builder.Default
    private String userSessionId = "none";

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private LoginType userLoginType = NORMAL;

    /* 쓴 댓글들 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Reply> replyList = new ArrayList<>();

    /* 프로필 사진들 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
    private List<UserProfile> userProfileList = new ArrayList<>();

    /* 포인트 증감 내역 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<Point> pointList = new ArrayList<>();

    // 양방향 매핑에서 리스트쪽에 데이터를 추가하는 편의메서드 생성
    public void addReplyList(Reply reply) {
        replyList.add(reply);
        if (this != reply.getUser()) {
            reply.setUser(this);
        }
    }

    public void addUserProfileList(UserProfile userProfile) {
        userProfileList.add(userProfile);
        if (this != userProfile.getUser()) {
            userProfile.setUser(this);
        }
    }

    public void addPoint(Point point) {
        pointList.add(point);
        if (this != point.getUser()) {
            point.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Accuse> accuseList = new ArrayList<>();

    public void addAccuseList(Accuse accuse) {
        accuseList.add(accuse);
        if (this != accuse.getUser()) {
            accuse.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList = new ArrayList<>();

    public void addAttendanceList(Attendance attendance) {
        attendanceList.add(attendance);
        if (this != attendance.getUser()) {
            attendance.setUser(this);
        }
    }
}
