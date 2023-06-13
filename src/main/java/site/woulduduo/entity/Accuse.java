package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "duo_accuse")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "accuseNo")
@ToString(exclude = {"user"})
public class Accuse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accuseNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_account")
    private User user;

    @Column(length = 100)
    private String accuseType;

    @Column(length = 20)
    private String accuseEtc;

}
