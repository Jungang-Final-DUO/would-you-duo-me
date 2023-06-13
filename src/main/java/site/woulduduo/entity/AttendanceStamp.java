package site.woulduduo.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "stampNo")
@Builder
@Entity
@Table(name = "duo_attendance_stamp")
public class AttendanceStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_no")
    private Long stampNo;

    //    onDelete = CascadeType.CASCADE) 맞을까...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @Column(name = "stamp_type", nullable = false, columnDefinition = "INT(1)")
    private Integer stampType;

    @CreatedDate
    @Column(name = "stamp_Month", updatable = false)
    private LocalDate stampMoth;

    // 지급 포인트 내역
    @OneToMany(mappedBy = "attendance_stamp", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Point> pointList = new ArrayList<>();


}