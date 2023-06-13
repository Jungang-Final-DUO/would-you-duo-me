package site.woulduduo.entity;


import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "profileNo")
@Builder
@Entity
@Table(name = "duo_user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @Column(length = 1000, nullable = false)
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User user;

}
