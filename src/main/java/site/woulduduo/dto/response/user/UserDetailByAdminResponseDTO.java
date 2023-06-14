package site.woulduduo.dto.response.user;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailByAdminResponseDTO {
    private String userAccount;
    private int boardCount;
    private int replyCount;
    private int reportCount;
    private int followCount;
    private LocalDate joinDate;
    private LocalDateTime recentLoginDate;
    private boolean isBanned;

}
