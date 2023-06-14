package site.woulduduo.dto.riot;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder
public class SummonerV4DTO {

    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private Long profileIconId;
    private Long revisionDate;
    private Long summonerLevel;

}
