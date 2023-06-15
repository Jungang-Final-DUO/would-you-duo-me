package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UserDetailByAdminResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.repository.UserRepository;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final AccuseRepository accuseRepository;
    private final ReplyRepository replyRepository;

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

    public List<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type){

        // Pageable객체 생성
        Pageable pageable = PageRequest.of(
                type.getPage() - 1,
                type.getSize(),

                Sort.by("createDate").descending()
        );

        //전체불러오기
        Page<User> all = userRepository.findAll(pageable);
        //user정보
        List<User> users = all.getContent();

        //dto리스트생성 및 dto 생성
        List<UsersByAdminResponseDTO> userListByAdmin = new ArrayList<>();
        UsersByAdminResponseDTO dto = new UsersByAdminResponseDTO();
        for (User user : users) {
            //bc,rc,rc,fc 카운터 찾는 메서드
            long accuseCount = accuseRepository.countByUser(user);
            long boardCount = boardRepository.countByUser(user);
            long replyCount = replyRepository.countByUser(user);



            dto.setUserAccount(user.getUserAccount());
            dto.setGender(user.getUserGender().toString());
            dto.setBoardCount(boardCount);
            dto.setReplyCount(replyCount);
            dto.setReportCount(accuseCount);
            dto.setPoint(user.getUserCurrentPoint());
            dto.setFollowCount(3);

            userListByAdmin.add(dto);
        }
        List<UsersByAdminResponseDTO> userListByAdmin1 = userListByAdmin;
        System.out.println("userListByAdmin1 = " + userListByAdmin1);

        return userListByAdmin1;
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
