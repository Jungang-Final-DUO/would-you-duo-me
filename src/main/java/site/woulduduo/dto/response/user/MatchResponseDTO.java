package site.woulduduo.dto.response.user;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class MatchResponseDTO {

    private int assists;
    private String championName;
    private int deaths;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int kills;
    private int mainPerk;
    private int subPerk;
    private int summoner1Id;
    private int summoner2Id;
    private boolean win;
    private String avgKda;

}
