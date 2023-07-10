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
@Table(name = "duo_recent_match")
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

    @Column(length = 3)
    private int kills;

    @Column(length = 3)
    private int deaths;

    @Column(length = 3)
    private int assists;

    @Column(length = 4)
    private int item0;

    @Column(length = 4)
    private int item1;

    @Column(length = 4)
    private int item2;

    @Column(length = 4)
    private int item3;

    @Column(length = 4)
    private int item4;

    @Column(length = 4)
    private int item5;

    @Column(length = 4)
    private int item6;

    @Column(length = 4)
    private int mainPerk;

    @Column(length = 4)
    private int subPerk;

    @Column(length = 2)
    private int summoner1Id;

    @Column(length = 2)
    private int summoner2Id;

    private boolean win;

    @Column(length = 6)
    private String avgKda;
}
