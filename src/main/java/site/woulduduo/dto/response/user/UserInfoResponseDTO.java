package site.woulduduo.dto.response.user;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserInfoResponseDTO {

    private String profileImage;
    private String userNickname;
    private String lolNickname;
    private Integer userCurrentPoint;

}
