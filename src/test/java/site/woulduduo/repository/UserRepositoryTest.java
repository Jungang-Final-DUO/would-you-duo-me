package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.dto.response.user.UsersByAdminResponseDTO;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.YEAR;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(false)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccuseRepository accuseRepository;

    @Test
    @DisplayName("전제 조회")
    void findAll(){
        List<User> all = userRepository.findAll();
        System.out.println("all = " + all);
    }

    @Test
    @DisplayName("사용자 저장")
    void saveTest(){
        User user2 = User.builder()
                .userAccount("123")
                .userNickname("123")
                .userPassword("123")
                .userCurrentPoint(123)
                .userBirthday(LocalDate.of(1999,11,07))
                .lolNickname("123")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
                .build();
        User user3 = User.builder()
                .userAccount("234")
                .userNickname("234")
                .userPassword("234")
                .userCurrentPoint(234)
                .userBirthday(LocalDate.of(1999,11,07))
                .lolNickname("234")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
                .build();
        User user4 = User.builder()
                .userAccount("345")
                .userNickname("345")
                .userPassword("345")
                .userCurrentPoint(345)
                .userBirthday(LocalDate.of(1999,11,07))
                .lolNickname("345")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
                .build();
        User save = userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);


        Accuse accuse = Accuse.builder()
                .accuseNo(1)
                .user(save)
                .accuseType("경고")
                .build();

        Accuse asave = accuseRepository.save(accuse);

    }

//    @Test
//    @DisplayName("사용자 포인트 조회")
//    void searchTest() {
//        Long point = userRepository.findByUserCurrentPoint("계정");
//        System.out.println("point = " + point);
//
//    }


}