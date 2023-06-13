package site.woulduduo.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "stampNo")
@Builder
@Entity
@Table(name = "duo_attendance_stamp")
public class AttdanceStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_no", length = 19)
    private Long stampNo;

    //    onDelete = CascadeType.CASCADE) 맞을까...
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account", nullable = false, referencedColumnName = "user_account", onDelete = CascadeType.CASCADE)
    private User user;


    @Column(name = "stamp_type", nullable = false, length = 1)
    private Integer stampType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate stampMoth;
}