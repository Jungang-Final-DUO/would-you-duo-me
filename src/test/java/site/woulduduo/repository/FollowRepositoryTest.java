package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.woulduduo.entity.Follow;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(false)
class FollowRepositoryTest {
    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserRepository userRepository;



    @Test
    @DisplayName("사용자 저장 및 팔로우설정")
    void saveTest() {
        User user2 = User.builder()
                .userAccount("123")
                .userNickname("123")
                .userPassword("123")
                .userCurrentPoint(123)
                .userBirthday(LocalDate.of(1999, 11, 07))
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
                .userBirthday(LocalDate.of(1999, 11, 07))
                .lolNickname("234")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
                .build();
        System.out.println("user2 = " + user2);
        System.out.println("user3 = " + user3);

        User save = userRepository.save(user2);
        User save1 = userRepository.save(user3);

        Follow follow1 = Follow.builder()
                .followTo(save)
                .followFrom(save1)
                .build();



        Follow save4 = followRepository.save(follow1);


    }
}