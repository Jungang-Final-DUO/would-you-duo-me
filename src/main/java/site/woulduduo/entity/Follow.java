package site.woulduduo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Setter
@Getter
@ToString(exclude = {})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "")
@Builder
@Entity
@Table(name = "duo_user_follow")
@IdClass(FollowPK.class)
public class Follow {
}
