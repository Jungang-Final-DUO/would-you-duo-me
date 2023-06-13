package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = {"board", "user", "attendanceList", "accuseList"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userAccount")
@Builder
@Entity
@Table(name = "duo_user")
@Check(constraints = "current_date - user_birthday >= 19")
public class User {

    @Id
    @Column(length = 50)
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
    private LoginType userLoginType = NORMAL;

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
