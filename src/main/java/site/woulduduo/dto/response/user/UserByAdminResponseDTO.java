package site.woulduduo.dto.response.user;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserByAdminResponseDTO {
    private int rowNum;
    private String userAccount;
    private String gender;
    private long boardCount;
    private long replyCount;
    private long reportCount;
    private int point;
    private long followCount;
    private LocalDate joinDate;

//    private UserByAdminResponseDTO(User user){
//        this.userAccount=user.getUserAccount();
//        this.gender=user.getUserGender().toString();
//        this.point=user.getUserCurrentPoint();
//        this.joinDate=user.getUserJoinDate();
//    }


}
