package site.woulduduo.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FollowPK implements Serializable {

    private User followFrom;

    private User followTo;
}
