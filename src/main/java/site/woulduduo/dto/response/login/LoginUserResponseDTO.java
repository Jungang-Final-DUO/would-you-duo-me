package site.woulduduo.dto.response.login;

import lombok.*;
import site.woulduduo.entity.UserProfile;
import site.woulduduo.enumeration.Position;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserResponseDTO {

    private String userAccount;

    private String userNickname;

    private String lolNickname;

    private Integer userCurrentPoint;

    private Position userPosition;

    private String userComment;

    private int userMatchingPoint;

    private String userProfileImage;

    // 프로필 사진들중에서 가장최신 1장만 뽑는 메서드
    private String getLatestProfileImage(List<UserProfile> userProfileList) {
        if (userProfileList != null && !userProfileList.isEmpty()) {
            UserProfile latestProfile = userProfileList.get(userProfileList.size() - 1);
            return latestProfile.getProfileImage();
        }
        return null;
    }
}
