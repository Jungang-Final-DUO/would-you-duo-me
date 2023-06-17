package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.dto.riot.MatchV5DTO;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserDUOResponseDTO {

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
    private Tier lolTier;

    // riot api 를 통해 얻어오는 데이터
    private int leaguePoints;
    private int totalWinCount;
    private int totalLoseCount;
    private double winRate;

    private int last20WinCount;
    private int last20LoseCount;
    private int last20WinRate;

    private double last20KDA;
    private double last20Kill;
    private double last20Death;
    private double last20Assist;

    private List<MostChampInfo> mostChampInfos;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    @Builder
    private static class MostChampInfo {
        private String champName;
        private int winCount;
        private int loseCount;
        private double winRate;
        private double KDA;
    }

    private List<MatchV5DTO> last20Matches;

    private List<UserReviewResponseDTO> userReviews;
}
