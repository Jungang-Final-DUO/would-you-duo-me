package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.entity.User;

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


    public UserByAdminResponseDTO(User user) {
        this.userAccount=user.getUserAccount();
        this.gender=user.getUserGender().toString();
        this.point=user.getUserCurrentPoint();
        this.boardCount=user.getBoardList().size();
        this.replyCount=user.getReplyList().size();
        this.reportCount=user.getAccuseList().size();
        this.followCount=user.getFollowToList().size();
        this.joinDate=user.getUserJoinDate();
    }
}
