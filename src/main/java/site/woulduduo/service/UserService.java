package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserDetailByAdminResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.repository.BoardRepository;
import site.woulduduo.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    final String id = "abc1234";
    public boolean registerDUO(/*HttpSession session, */UserCommentRequestDTO dto) {

        User exUser = User.builder()
                .userSessionId("abc1234@ddd.com")
                .userAccount("abc1234@ddd.com")
                .userPassword("12345678")
                .lolTier(Tier.CHA)
                .userGender(Gender.M)
                .userBirthday(LocalDate.of(2000, 03, 16))
                .userNickname("HongChaa")
                .lolNickname("HongChaa")
                .build();

        userRepository.save(exUser);

        Optional<User> user = userRepository.findById(exUser.getUserSessionId());

        System.out.println("user = " + user);
        if(user.isEmpty()) {
            return false;
        }
        user.ifPresent(u -> {
            u.setUserPosition(dto.getUserPosition());
            u.setUserComment(dto.getUserComment());
            u.setUserMatchingPoint(dto.getUserMatchingPoint());

            userRepository.save(u);
        });
        return true;
    }

    public ListResponseDTO<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type){

        List<User> all = userRepository.findAll();
            all.forEach(user -> {

                //userPoint 찾기
                Long byUserCurrentPoint = userRepository.findByUserCurrentPoint(user.getUserAccount());


                UsersByAdminResponseDTO dto
                        =new UsersByAdminResponseDTO();
                dto.setUserAccount(user.getUserAccount());
                dto.setGender(user.getUserGender().toString());
//                dto.setBoardCount();
//                dto.setReplyCount();
//                dto.setReportCount();
                dto.setPoint(user.getUserCurrentPoint());
//                dto.setFollowCount();
            });
        }

        return null;
    }


//    public UserDetailByAdminResponseDTO getUserDetailByAdmin(String userAccount){
//
//        return null;
//    }
//
//    public boolean increaseUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }
//
//    public boolean changeUserPoint(UserModifyRequestDTO dto){
//
//        return false;
//    }



}
