package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.FetchType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "recent_match")
public class RecentMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recent_match_no")
    private Long recentMatchNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

    @Column(length = 2)
    private int recentNo;

    @Column(length = 20)
    private String championName;

}
