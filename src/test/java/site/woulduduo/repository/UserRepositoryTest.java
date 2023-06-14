package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;

import static java.util.Calendar.YEAR;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자 저장")
    void saveTest(){

        Accuse accuse = Accuse.builder()
                .user("계정")
                .accuseType("경고")
                .build();

//        User user = User.builder()
//                .userAccount("우리")
//                .userNickname("저기")
//                .userPassword("123123")
//                .userCurrentPoint(11111)
//                .userBirthday(LocalDate.of(2000,11,07))
//                .lolNickname("네임")
//                .userGender(Gender.F)
//                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
//                .build();

        User save = userRepository.save(user);
        assertNotNull(save);
    }

//    @Test
//    @DisplayName("사용자 포인트 조회")
//    void searchTest() {
//        Long point = userRepository.findByUserCurrentPoint("계정");
//        System.out.println("point = " + point);
//
//    }


}