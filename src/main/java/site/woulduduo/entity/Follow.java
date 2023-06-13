package site.woulduduo.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "duo_user_follow")
@IdClass(FollowPK.class)
public class Follow {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_from")
    private User followFrom;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follow_to")
    private User followTo;

}
