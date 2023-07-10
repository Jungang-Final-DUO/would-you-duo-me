package site.woulduduo.dto.riot;

import lombok.*;
import site.woulduduo.enumeration.Tier;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class TierAndRank {

    private Tier tier;

    private int Rank;

}
