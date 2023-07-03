package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailByAdminResponseDTO {
    private String userNickname;
    private long boardCount;
    private long replyCount;
    private long reportCount;
    private long followCount;
    private LocalDate joinDate;
    private String  recentLoginDate;
    private boolean isBanned;
    private int point;

    public UserDetailByAdminResponseDTO(User user){
        this.userNickname= user.getUserNickname();
        this.boardCount=user.getBoardList().size();
        this.replyCount=user.getReplyList().size();
        this.reportCount=user.getAccuseList().size();
        this.followCount=user.getFollowToList().size();
        this.joinDate=user.getUserJoinDate();
        this.recentLoginDate=recentLogin(user);
        this.isBanned=user.isUserIsBanned();
        this.point=user.getUserCurrentPoint();
    }

    public boolean isBanned(){
        if(this.isBanned!=false){
            return false;
        }  return true;

    }

    public String   recentLogin(User user){
        LocalDateTime userRecentLoginDate = user.getUserRecentLoginDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = userRecentLoginDate.format(formatter);
        return format;
    }

}
