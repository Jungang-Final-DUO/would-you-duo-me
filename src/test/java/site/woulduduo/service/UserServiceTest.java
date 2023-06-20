package site.woulduduo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.request.page.PageDTO;
import site.woulduduo.dto.request.page.UserSearchType;
import site.woulduduo.dto.request.user.UserCommentRequestDTO;
import site.woulduduo.dto.request.user.UserModifyRequestDTO;
import site.woulduduo.dto.request.user.UserRegisterRequestDTO;
import site.woulduduo.dto.response.ListResponseDTO;
import site.woulduduo.dto.response.user.*;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Position;
import site.woulduduo.enumeration.Tier;
import site.woulduduo.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void userInsert() {
        for (int i = 1; i < 42; i++) {
            User user = User.builder()
                    .userAccount("user" + i)
                    .userNickname("nickname" + i)
                    .userPassword("pwd" + i)
                    .userBirthday(LocalDate.of(2000, 1, 1))
                    .lolNickname("lolNickname" + i)
                    .userGender(Gender.M)
                    .lolTier(Tier.CHA)
                    .userPosition(Position.MID)
                    .userComment("안녕하세요 트롤아닙니다." + i)
                    .userMatchingPoint(500)
                    .build();
            userRepository.save(user);
        }
        for (int i = 42; i < 100; i++) {
            User user = User.builder()
                    .userAccount("user" + i)
                    .userNickname("nickname" + i)
                    .userPassword("pwd" + i)
                    .userBirthday(LocalDate.of(2000, 1, 1))
                    .lolNickname("lolNickname" + i)
                    .userGender(Gender.M)
                    .lolTier(Tier.DIA)
                    .userJoinDate(LocalDate.of(2023,06,20))
                    .userPosition(Position.MID)
                    .userComment("안녕하세요 트롤아닙니다." + i)
                    .userMatchingPoint(500)
                    .build();
            userRepository.save(user);
        }

        User user = User.builder()
                .userAccount("345")
                .userNickname("345")
                .userPassword("345")
                .userBirthday(LocalDate.of(2000, 1, 1))
                .lolNickname("345")
                .userGender(Gender.M)
                .lolTier(Tier.DIA)
                .userJoinDate(LocalDate.of(2023,06,20))
                .userPosition(Position.MID)
                .userComment("345")
                .userMatchingPoint(500)
                .build();
        userRepository.save(user);



    }

    @Test
    @DisplayName("QueryDSL을 이용해 필터와 정렬 조건에 맞춰 userList가 출력되어야한다.")
    void testGetUserProfileList() {
        UserSearchType userSearchType = new UserSearchType();
        userSearchType.setPosition(Position.MID);
        userSearchType.setGender(Gender.M);
        userSearchType.setTier(Tier.DIA);
        userSearchType.setSort("avgRate");

        List<UserProfilesResponseDTO> userProfileList = userService.getUserProfileList(userSearchType);

        assertEquals(userProfileList.size(), 40);
    }

    @Test
    @DisplayName("유요한 사용자 정보로 회원 가입 테스트")
    void testRegisterWithValidUserInfo() {
        // Given
        UserRegisterRequestDTO dto = UserRegisterRequestDTO.builder()
                .userEmail("test1@example.com")
                .userPassword("abC123@")
                .userNickname("송유근")
                .userBirthday(LocalDate.of(2000, 1, 1))
                .lolNickname("결속의반지")
                .userGender(Gender.M)
                .build();

        // When
        userService.register(dto);

        // Then
        assertEquals(1, userRepository.count());
    }

    @Test
    @DisplayName("이미 등록된 이메일로 회원 가입 시도")
    void testRegisterWithDuplicateEmail() {
        // Given
        UserRegisterRequestDTO dto1 = UserRegisterRequestDTO.builder()
                .userEmail("test@example.com")
                .userPassword("password123")
                .userNickname("nickname1")
                .userBirthday(LocalDate.of(2000, 1, 1))
                .lolNickname("lolnickname1")
                .userGender(Gender.M)
                .build();

        UserRegisterRequestDTO dto2 = UserRegisterRequestDTO.builder()
                .userEmail("test@example.com")
                .userPassword("password456")
                .userNickname("nickname2")
                .userBirthday(LocalDate.of(2000, 2, 2))
                .lolNickname("lolnickname2")
                .userGender(Gender.F)
                .build();

        // When
        userService.register(dto1);

        // Then
        assertThrows(IllegalArgumentException.class, () -> userService.register(dto2));
    }

    @Test
    @DisplayName("이미 등록된 닉네임으로 회원 가입 시도")
    void testRegisterWithDuplicateNickname() {
        // Given
        UserRegisterRequestDTO dto1 = UserRegisterRequestDTO.builder()
                .userEmail("test1@example.com")
                .userPassword("password123")
                .userNickname("nickname")
                .userBirthday(LocalDate.of(2000, 1, 1))
                .lolNickname("lolnickname1")
                .userGender(Gender.M)
                .build();

        UserRegisterRequestDTO dto2 = UserRegisterRequestDTO.builder()
                .userEmail("test2@example.com")
                .userPassword("password456")
                .userNickname("nickname")
                .userBirthday(LocalDate.of(2000, 2, 2))
                .lolNickname("lolnickname2")
                .userGender(Gender.F)
                .build();

        // When
        userService.register(dto1);

        // Then
        assertThrows(IllegalArgumentException.class, () -> userService.register(dto2));
    }

    @Test
    @DisplayName("관리자페이지 정보 count 확인")
    void getCountByAdmin() {
        AdminPageResponseDTO adminPageInfo = userService.getAdminPageInfo();
        System.out.println("stringIntegerMap = " + adminPageInfo);
    }


    @Test
    @DisplayName("관리자 전체유저 리스트 dto 변환+페이징")
    void userExchangeDTO(){


        PageDTO dto = new PageDTO();

        System.out.println("dto = " + dto);
        ListResponseDTO<UserByAdminResponseDTO, User> userListByAdmin = userService.getUserListByAdmin(dto);

        System.out.println("userListByAdmin = " + userListByAdmin);


    }


    @Test
    @DisplayName("관리자 금일가입유저 리스트 dto 변환")
    void todayUserExchangeDTO(){


        PageDTO dto = new PageDTO();


        ListResponseDTO<UserByAdminResponseDTO, User> userByAdminResponseDTOUserListResponseDTO = userService.todayUserByAdMin(dto);

        System.out.println("userByAdminResponseDTOUserListResponseDTO = " + userByAdminResponseDTOUserListResponseDTO);


    }

    @Test
    @DisplayName("유저 디테일 dto 변환")
    void getUserDetailByAdmin(){


        UserDetailByAdminResponseDTO UserByAdmin =
                userService.getUserDetailByAdmin("345");

        System.out.println("todayUserListByAdmin = " + UserByAdmin);

    }

    @Test
    @DisplayName("포인트 증가")
    void increaseUserPoint() {
        UserModifyRequestDTO modify = UserModifyRequestDTO.builder()
                .userNickname("123")
                .userBirthday(LocalDate.of(2012, 01, 01))
                .lolNickname("아무나")
                .userPassword("123")
                .userInstagram("123")
                .userFacebook("123")
                .userTwitter("123")
                .userCurrentPoint(123123)
                .userAddPoint(33333)
                .userIsBanned(0)
                .build();



        System.out.println("modify1 = " + modify);
        boolean b123 = userService.increaseUserPoint(modify);
        System.out.println("b123 = " + b123);
        System.out.println("modify2 = " + modify);

    }

    @Test
    @DisplayName("밴 boolean")
    void changeBanStatus() {
        UserModifyRequestDTO modify = UserModifyRequestDTO.builder()
                .userNickname("123")
                .userBirthday(LocalDate.of(2012, 01, 01))
                .lolNickname("아무나")
                .userPassword("123")
                .userInstagram("123")
                .userFacebook("123")
                .userTwitter("123")
                .userCurrentPoint(123123)
                .userAddPoint(33333)
                .userIsBanned(0)
                .build();

        System.out.println("modify1 = " + modify);
        boolean b123 = userService.changeBanStatus(modify);
        System.out.println("b123 = " + b123);
        System.out.println("modify2 = " + modify);

    }

    @Test
    @DisplayName("회원의 프로필 카드 등록에 성공해야 한다.")
    void registerDUO() {
        UserCommentRequestDTO userCommentRequestDTO = UserCommentRequestDTO.builder()
                .userPosition(Position.MID)
                .userComment("안녕하세요 트롤아닙니다.")
                .userMatchingPoint(500)
                .build();

        boolean b = userService.registerDUO(userCommentRequestDTO);

        assertTrue(b);
    }

    @Test
    @DisplayName("유저 전적페이지에 쓰이는 정보들을 보여줄 수 있어야 한다")
    void getUserDUOInfoTest() {
        UserHistoryResponseDTO userDUOInfo = userService.getUserHistoryInfo(null, "test@example.com");
        System.out.println("userDUOInfo = " + userDUOInfo);
    }



}