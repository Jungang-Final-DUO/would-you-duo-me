package site.woulduduo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "duo_attendance")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "attendanceNo")
@ToString(exclude = {"user"})

public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attendanceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate attendanceDate;

}
