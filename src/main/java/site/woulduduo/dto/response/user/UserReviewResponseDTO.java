package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.entity.Matching;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserReviewResponseDTO {

    private String userAccount;
    private String profileImage;
    private Integer matchingReviewRate;
    private String matchingReviewContent;

    public UserReviewResponseDTO(Matching matching) {
        this.userAccount = matching.getChatting().getChattingFrom().getUserAccount();
        this.profileImage = matching.getChatting().getChattingFrom().getLatestProfileImage();
        this.matchingReviewRate = matching.getMatchingReviewRate();
        this.matchingReviewContent = matching.getMatchingReviewContent();
    }

}
