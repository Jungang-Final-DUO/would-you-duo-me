package site.woulduduo.entity;

import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FollowPK implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User followFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account")
    private User followTo;
}
