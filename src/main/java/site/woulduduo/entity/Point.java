package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = {""})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "")
@Builder
@Entity
@Table(name = "duo_point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_no")
    private Long pointNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @Column(name = "point_amount", nullable = false)
    private Integer pointAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatting_no")
    private Chatting chatting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_no")
    private Matching matching;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ad_no")
//    private Ad ad;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "stamp_no")
//    private AttendanceStamp attendanceStamp;

}
