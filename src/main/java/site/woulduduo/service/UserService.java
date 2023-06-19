package site.woulduduo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.page.AdminSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RiotApiService riotApiService;

    final String id = "abc1234";

    public void register(UserRegisterRequestDTO dto) {

        // 이메일 중복 검사
        if (userRepository.countByUserEmail(dto.getUserEmail()) > 0) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        // 닉네임 중복 검사
        if (userRepository.countByUserNickname(dto.getUserNickname()) > 0) {
            throw new IllegalArgumentException("이미 등록된 닉네임입니다.");
        }

        // 소환사 아이디 중복 검사
        if (userRepository.countByLolNickname(dto.getLolNickname()) > 0) {
            throw new IllegalArgumentException("이미 등록된 롤 닉네임입니다.");
        }

        // 회원 정보 저장
        User user = User.builder()
                .userAccount(dto.getUserEmail())
                .userNickname(dto.getUserNickname())
                .userPassword(passwordEncoder.encode(dto.getUserPassword()))
                .userBirthday(dto.getUserBirthday())
                .userInstagram(dto.getUserInstagram())
                .userTwitter(dto.getUserTwitter())
                .userFacebook(dto.getUserFacebook())
                .lolNickname(dto.getLolNickname())
                .userGender(dto.getUserGender() == Gender.M ? Gender.M : Gender.F)
                .lolTier(riotApiService.getTier(dto.getLolNickname()))
                .build();

        userRepository.save(user);

        log.info("회원 가입이 완료되었습니다.");
    }

    // 중복검사 서비스 처리
    public int checkSignUpValue(String type, String keyword) {
        int flagNum;

        switch (type) {
            case "email":
                flagNum = userRepository.countByUserEmail(keyword);
                break;
            case "nickname":
                flagNum = userRepository.countByUserNickname(keyword);
                break;
            case "lolNickname":
                flagNum = userRepository.countByLolNickname(keyword);
                break;
            default:
                throw new IllegalArgumentException("잘못된 검사 타입입니다.");
        }

        return flagNum;
    }





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
        if (user.isEmpty()) {
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

    public ListResponseDTO<UsersByAdminResponseDTO> getUserListByAdmin(AdminSearchType type) {
        userRepository.count();
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
