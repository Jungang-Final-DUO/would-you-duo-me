package site.woulduduo.dto.response.user;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserReviewResponseDTO {

    private String userAccount;
    private String profileImage;
    private String matchingReviewRate;
    private String matchingReviewContent;

}
