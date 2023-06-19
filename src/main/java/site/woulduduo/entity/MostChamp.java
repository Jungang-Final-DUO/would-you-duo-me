package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "champNo")
@Builder
@Entity
@Table(name = "duo_most_champ")
public class MostChamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "champ_no")
    private Long champNo;

    @Column(name = "champ_name", nullable = false, length = 50)
    private String champName;

    @Column(name = "most_no", nullable = false)
    private int mostNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;


}
