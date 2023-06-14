package site.woulduduo.dto.response.user;

import lombok.*;
import site.woulduduo.entity.User;
import site.woulduduo.repository.UserRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsersByAdminResponseDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        this.userAccount=user.getUserAccount();
        this.gender=user.getUserGender().toString();
        this.point=user.getUserCurrentPoint();
        this.joinDate=user.getUserJoinDate();
    }


}