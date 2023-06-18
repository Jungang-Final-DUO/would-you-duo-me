package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.dto.riot.MatchV5DTO;
import site.woulduduo.dto.riot.MostChampInfo;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserHistoryResponseDTO {

    // 데이터베이스에서 가져오는 유저 정보
    private String userAccount;
    // 가장 나중에 추가된 프로필사진
    private String profileImage;
    private String userNickname;
    private Position userPosition;
    // session 에 로그인된 사용자가 이 사용자를 팔로우했는지 여부
    private boolean isFollowed;
    private Double userAvgRate;
    private Integer userMatchingPoint;
    private String userInstagram;
    private String userFacebook;
    private String userTwitter;
    private String lolNickname;
    private String userComment;
    private String tier;

    // riot api 를 통해 얻어오는 데이터
    private int leaguePoints;
    private int totalWinCount;
    private int totalLoseCount;
    private double winRate;

    private int last20WinCount;
    private int last20LoseCount;
    private double last20WinRate;

    private double last20KDA;
    private double last20Kill;
    private double last20Death;
    private double last20Assist;

    private List<MostChampInfo> mostChampInfos;

    private List<MatchV5DTO.MatchInfo.ParticipantDTO> last20Matches;

    private List<UserReviewResponseDTO> userReviews;

    // 빌더 커스텀
    public static class UserHistoryResponseDTOBuilder {

        public UserHistoryResponseDTOBuilder tier(Tier tier) {
            switch (tier) {
                case UNR:
                    this.tier = "Challenger";
                    break;
                case IRO:
                    this.tier = "Iron";
                    break;
                case BRO:
                    this.tier = "Bronze";
                    break;
                case SIL:
                    this.tier = "Silver";
                    break;
                case GOL:
                    this.tier = "Gold.png";
                    break;
                case PLA:
                    this.tier = "Platinum";
                    break;
                case DIA:
                    this.tier = "Diamond";
                    break;
                case MAS:
                    this.tier = "Master";
                    break;
                case GRA:
                    this.tier = "Grandmaster";
                    break;
                default:
                    this.tier = null;
            }
            return this;
        }

        public UserHistoryResponseDTOBuilder last20Matches(List<MatchV5DTO.MatchInfo.ParticipantDTO> last20Matches) {
            this.last20Matches = last20Matches;
            this.last20WinCount = (int) last20Matches.stream()
                    .filter(MatchV5DTO.MatchInfo.ParticipantDTO::isWin).count();
            this.last20LoseCount = (int) last20Matches.stream()
                    .filter(m -> !m.isWin()).count();
            this.last20WinRate = (double) this.last20WinCount / (this.last20WinCount + this.last20LoseCount);
            this.last20KDA = last20Matches.stream()
                    .mapToDouble(m -> ((double) m.getKills() + m.getAssists()) / m.getDeaths())
                    .average().orElse(0.0);
            this.last20Kill = last20Matches.stream()
                    .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getKills)
                    .sum();
            this.last20Death = last20Matches.stream()
                    .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getDeaths)
                    .sum();
            this.last20Assist = last20Matches.stream()
                    .mapToInt(MatchV5DTO.MatchInfo.ParticipantDTO::getAssists)
                    .sum();

            return this;
        }
    }
}
