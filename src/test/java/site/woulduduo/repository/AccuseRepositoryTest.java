package site.woulduduo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.woulduduo.entity.Accuse;
import site.woulduduo.entity.User;
import site.woulduduo.enumeration.Gender;
import site.woulduduo.enumeration.Tier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccuseRepositoryTest {
    @Autowired
    AccuseRepository accuseRepository;

    @Test
    @DisplayName("사용자 저장")
    void saveTest(){



        User user1 = User.builder()
                .userAccount("우리")
                .userNickname("저기")
                .userPassword("123123")
                .userCurrentPoint(11111)
                .userBirthday(LocalDate.of(2000,11,07))
                .lolNickname("네임")
                .userGender(Gender.F)
                .lolTier(Tier.DIA)
//                .accuseList(Accuse)
                .build();

        Accuse accuse = Accuse.builder()
                .user(user1)
                .accuseType("경고")
                .accuseEtc("경고")
                .build();
        System.out.println("accuse = " + accuse);

        Accuse asave = accuseRepository.save(accuse);
//        User save = userRepository.save(user1);
//        assertNotNull(asave);
    }

}