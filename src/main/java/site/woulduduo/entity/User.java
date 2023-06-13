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
import java.util.List;

import static site.woulduduo.enumeration.LoginType.NORMAL;

@Setter
@Getter
@ToString(exclude = {"replyList", "userProfileList"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userAccount")
@Builder
@Entity
@Table(name = "duo_user")
@Check(constraints = "current_date - user_birthday >= 19")
public class User {

    @Id
    @Column(name = "user_account",length = 50)
    private String userAccount;

    @Column(length = 15, nullable = false, unique = true)
    private String userNickname;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private LocalDate userBirthday;

    @Column(length = 20, nullable = false, unique = true)
    private String lolNickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender userGender;

    @CreationTimestamp
    @Column(updatable = false)
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LoginType userLoginType = NORMAL;

    /* 쓴 댓글들 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reply> replyList;

    /* 프로필 사진들 */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserProfile> userProfileList;

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

}
