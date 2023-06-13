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
    @Column(name="accuse_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accuseNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_account")
    private User user;

    @Column(length = 100, name="accuse_type")
    private String accuseType;

    @Column(length = 20,name="accuse_etc")
    private String accuseEtc;

}
