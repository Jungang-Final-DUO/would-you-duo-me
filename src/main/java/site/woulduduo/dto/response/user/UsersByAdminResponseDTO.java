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
public class UsersByAdminResponseDTO {
    private int rowNum;
    private String userAccount;
    private String gender;
    private int boardCount;
    private int replyCount;
    private int reportCount;
    private int point;
    private int followCount;
    private LocalDate joinDate;

    private UsersByAdminResponseDTO(User user){
        this.rowNum=1;
        this.userAccount=user.getUserAccount();
        this.gender=user.getUserGender().toString();
        this.boardCount=userGetBoardCount(user);
        this.replyCount=userGetreplyCount(user);
        this.reportCount=userGetreportCount(user);
        this.point=user.getUserCurrentPoint();
        this.followCount=getUserPoint();


    }

    private int getUserPoint() {
        return 0;
    }

    private int userGetreportCount(User user) {

    return 0;
    };

    public int userGetBoardCount(User user){
        user.getBoardList();
        return 1;
    }
    public int userGetreplyCount(User user){
        user.getReplyList();
        return 1;
    }

}
